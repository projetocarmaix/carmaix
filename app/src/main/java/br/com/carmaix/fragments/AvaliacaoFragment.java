package br.com.carmaix.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliacaoVisualizarActivity;
import br.com.carmaix.adapters.AvaliacaoAdapter;
import br.com.carmaix.model.Model;
import br.com.carmaix.services.AvaliationReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.MethodType;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoFragment extends BaseFragment implements SearchView.OnQueryTextListener {

    private int countOffset = 1;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<AvaliationReturn> avaliationReturns;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;
    private String status;
    private int tab;
    protected Model model = null;
    private AvaliacaoAdapter avaliacaoAdapter = new AvaliacaoAdapter(fragmentActivity, new ArrayList<AvaliationReturn>());
    private String queryString;

    @SuppressLint("ValidFragment")
    public AvaliacaoFragment(int tab) {
        this.tab = tab;

        if(tab == Constants.TAB_CINZA) {
            status = Constants.STATUS_NAO_AVALIADO;
        }else if(tab == Constants.TAB_VERMELHA) {
            status = Constants.STATUS_AVALIADO;
        }else if(tab == Constants.TAB_LARANJA) {
            status = Constants.STATUS_PROPOSTA;
        }else if(tab == Constants.TAB_VERDE) {
            status = Constants.STATUS_ESTOQUE;
        }else if(tab == Constants.TAB_ROXO) {
            status = Constants.STATUS_VENDIDO;
        }

        this.queryString = "";

    }

    public AvaliacaoFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avaliacao_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.avaliacaoRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mProgressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }

                if (dy > 0) { //check for scroll down
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        actionLast();
                    }
                }
            }

        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                countOffset = 1;
                queryString = "";
                showProgressBar();

                runBackground("", false, true, Constants.ACTION_REFRESH);

            }

        });

        registerForContextMenu(recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        this.model = new Model();

        showProgressBar();


        runBackground("", false, true, Constants.ACTION_LIST);

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        countOffset = 1;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent i = new Intent(getContext(), AvaliacaoVisualizarActivity.class);
        startActivity(i);
        return true;
    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {

        if (action == Constants.ACTION_LIST || action == Constants.ACTION_REFRESH) {

            Thread.sleep(5000);

            ArrayList<AvaliationReturn> avaliationReturns = null;

            if (action == Constants.ACTION_LIST) {

                avaliationReturns = CallService.listAvaliations(fragmentActivity, MethodType.CACHE_YES, Constants.MAX_ITEMS, 0, status, "", "");

                if (avaliationReturns == null || avaliationReturns.size() == 0) {

                    avaliationReturns = CallService.listAvaliations(fragmentActivity, MethodType.CACHE_NO, Constants.MAX_ITEMS, 0, status, "", "");

                    this.model.setVerifiCache(false);

                } else {
                    this.model.setVerifiCache(true);
                }

            } else if (action == Constants.ACTION_REFRESH) {

                avaliationReturns = CallService.listAvaliations(fragmentActivity, MethodType.CACHE_NO, Constants.MAX_ITEMS, 0, status, "", "");

            }

            this.avaliationReturns = avaliationReturns;

        } else if (action == Constants.ACTION_LIST_SERVER) {

            ArrayList<AvaliationReturn> avaliationReturns = null;

            avaliationReturns = CallService.listAvaliations(fragmentActivity, MethodType.CACHE_NO, Constants.MAX_ITEMS, 0, status, "", "");

            if (avaliationReturns != null && avaliationReturns.size() > 0) {

                this.model.setRefreshListData(true);
                this.avaliationReturns = avaliationReturns;

            } else {

                // Ocorre quando existe dados no cache
                if (avaliationReturns == null) {
                    this.model.setRefreshListData(false);
                } else {

                    // Os dados foram sobrescritos e devem ser readicionados
                    this.model.setRefreshListData(true);
                    this.avaliationReturns = avaliationReturns;

                }

            }

        } else if (action == Constants.ACTION_LIST_OLDER) {
            loadOlder();
        }

        super.backgroundMethod(action);

    }

    @Override
    protected void onEndBackgroundRun(int action) {

        if (action == Constants.ACTION_LIST || action == Constants.ACTION_REFRESH) {

            endBackGroundList();

            if (action == Constants.ACTION_LIST) {

                if (this.model.isVerifiCache()) {
                    runBackground("", false, true, Constants.ACTION_LIST_SERVER);
                } else {

                    hideProgressBar();

                    if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
                    }

                }

            } else {

                mSwipeRefreshLayout.setRefreshing(false);

                hideProgressBar();

                if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
                }

            }
        } else if (action == Constants.ACTION_LIST_SERVER) {

            hideProgressBar();

            if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
            }

            if (model.isRefreshListData()) {
                endBackGroundList();
            }

        } else if (action == Constants.ACTION_LIST_OLDER) {
            hideProgressBar();
        }

        super.onEndBackgroundRun(action);

    }

    @Override
    protected void onBackGroundMethodException(Throwable e, boolean highPriority, int action, Object... params) {

        e.printStackTrace();


        hideProgressBar();

        if (action == Constants.ACTION_REFRESH) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        super.onBackGroundMethodException(e, highPriority, action, params);

    }

    protected void endBackGroundList() {
        inputDataAdapter();
    }

    public void inputDataAdapter() {

        avaliacaoAdapter = new AvaliacaoAdapter(fragmentActivity, avaliationReturns);

        recyclerView.setAdapter(avaliacaoAdapter);

    }

    protected void showProgressBar() {

        try {
            if (!isVisibleProgressBar()) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    protected boolean isVisibleProgressBar() {

        if (this.mProgressBar.getVisibility() == View.GONE || mProgressBar.getVisibility() == View.INVISIBLE) {
            return false;
        }

        return true;
    }

    protected void actionLast() {

        if (avaliacaoAdapter != null && (avaliacaoAdapter.getItemCount() >= (Constants.MAX_ITEMS * countOffset))) {

            countOffset++;
            showProgressBar();
            if(queryString.isEmpty()) {
                runBackground("", false, true, Constants.ACTION_LIST_OLDER);
            }else {
                runBackgroundParams("", false, true, Constants.ACTION_SEARCH_LIST, queryString, avaliacaoAdapter.getItemCount());
            }
        }

    }

    private void loadOlder() throws Exception {

        try {
            ArrayList<AvaliationReturn> avaliationReturns = CallService.listAvaliations(fragmentActivity, MethodType.CACHE_NO, Constants.MAX_ITEMS, avaliacaoAdapter.getItemCount(), status, "", "");

            if (avaliationReturns != null && avaliationReturns.size() > 0){
                this.avaliationReturns.addAll(avaliationReturns);

                ReceiverThread receiverThread = new ReceiverThread();
                receiverThread.run();
            }else {
                countOffset = countOffset - 1;
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        setupSearchView(menuItem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
    }

    private void setupSearchView(MenuItem searchItem) {
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void search(String query) {

        showProgressBar();

        queryString = query;
        runBackgroundParams("", false, true, Constants.ACTION_SEARCH, query);

    }

    @Override
    protected void backgroundMethod(int action, Object... params) throws Throwable {
        String query = (String) params[0];

        if (action == Constants.ACTION_SEARCH){

            ArrayList<AvaliationReturn> avaliationReturns = CallService.searchAvaliations(fragmentActivity, MethodType.CACHE_NO, query, Constants.MAX_ITEMS, 0, status, "", "");

            this.avaliationReturns = avaliationReturns;

        }

        if (action == Constants.ACTION_SEARCH_LIST) {
            loadResultQuery(query);
        }

        super.backgroundMethod(action, params);

    }

    private void loadResultQuery(String query) throws Exception {
        try {
            ArrayList<AvaliationReturn> avaliationReturns = CallService.searchAvaliations(fragmentActivity, MethodType.CACHE_NO, query, Constants.MAX_ITEMS, avaliacaoAdapter.getItemCount(), status, "", "");

            if (avaliationReturns != null && avaliationReturns.size() > 0){
                this.avaliationReturns.addAll(avaliationReturns);

                ReceiverThread receiverThread = new ReceiverThread();
                receiverThread.run();
            }else {
                countOffset = countOffset - 1;
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    protected void onEndBackgroundRun(int action, Object... params) {

        if (action == Constants.ACTION_SEARCH) {

            endBackGroundList();

            if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
            }

            hideProgressBar();

        }

        if (action == Constants.ACTION_SEARCH_LIST) {

            if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
            }

            hideProgressBar();
        }

        super.onEndBackgroundRun(action);

    }

    private class ReceiverThread extends Thread {
        @Override
        public void run() {
            fragmentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    avaliacaoAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}