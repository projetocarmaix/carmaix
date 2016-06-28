package br.com.carmaix.fragments;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliacaoVisualizarActivity;
import br.com.carmaix.adapters.AvaliacaoAdapter;
import br.com.carmaix.domain.Avaliacao;
import br.com.carmaix.domain.AvaliacaoService;
import br.com.carmaix.model.Model;
import br.com.carmaix.services.AvaliationReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.ListAvaliationReturn;
import br.com.carmaix.services.MethodType;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.view.EmptyRecyclerView;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoFragment extends BaseFragment {

    private int countOffset = 1;

    private boolean loading = true;

    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private LinearLayoutManager linearLayoutManager;

    private ArrayList<AvaliationReturn> avaliationReturns;

    private EmptyRecyclerView recyclerView;

    private View layoutEmpty = null;

    private TextView emptyTextView = null;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ProgressBar mProgressBar;

    private String status;

    protected Model model = null;

    private AvaliacaoAdapter avaliacaoAdapter = new AvaliacaoAdapter(fragmentActivity, new ArrayList<AvaliationReturn>());

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.avaliacao_fragment, container, false);

        recyclerView = (EmptyRecyclerView) view.findViewById(R.id.avaliacaoRecyclerView);

        layoutEmpty = view.findViewById(R.id.item_empty_list);

        emptyTextView = (TextView) view.findViewById(R.id.textEmpty);

        recyclerView.setEmptyView(layoutEmpty);

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

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {

                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {

                            loading = false;

                            actionLast();

                        }
                    }
                } else {
                    loading = true;
                }

            }

        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                countOffset = 1;

                showProgressBar();

                emptyTextView.setText(fragmentActivity.getString(R.string.loadingEmpty));

                runBackground("", false, true, Constants.ACTION_REFRESH);

            }

        });

        registerForContextMenu(recyclerView);

        if (this.getArguments() != null) {
            status = (String) this.getArguments().get("status");
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        this.model = new Model();

        showProgressBar();

        emptyTextView.setText(fragmentActivity.getString(R.string.loadingEmpty));

        runBackground("", false, true, Constants.ACTION_LIST);

        super.onActivityCreated(savedInstanceState);
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
            loadOlder(action);
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
                        emptyTextView.setText(fragmentActivity.getString(R.string.emptyValues));
                    }

                }

            } else {

                mSwipeRefreshLayout.setRefreshing(false);

                hideProgressBar();

                if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
                    emptyTextView.setText(fragmentActivity.getString(R.string.emptyValues));
                }

            }

        } else if (action == Constants.ACTION_LIST_SERVER) {

            hideProgressBar();

            if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
                emptyTextView.setText(fragmentActivity.getString(R.string.emptyValues));
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

        emptyTextView.setText(fragmentActivity.getString(R.string.errorServer));

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

        if (avaliacaoAdapter != null && avaliacaoAdapter.getItemCount() >= Constants.MAX_ITEMS * countOffset) {

            countOffset++;

            showProgressBar();

            runBackground("", false, true, Constants.ACTION_LIST_OLDER);

        }

    }

    private void loadOlder(int action) throws Exception {

        try {

            ArrayList<AvaliationReturn> avaliationReturns = CallService.listAvaliations(fragmentActivity, MethodType.CACHE_NO, Constants.MAX_ITEMS, avaliacaoAdapter.getItemCount(), status, "", "");

            if (avaliationReturns != null && avaliationReturns.size() > 0){
                this.avaliationReturns.addAll(avaliationReturns);
            }

            Runnable finishedLoadingOldestTask = new LoadOldestResult(avaliationReturns);

            handler.post(finishedLoadingOldestTask);

        } catch (Exception e) {
            countOffset = countOffset - 1;
            throw e;
        }

    }

    private class LoadOldestResult implements Runnable {

        private ArrayList<AvaliationReturn> avaliationReturns;

        public LoadOldestResult(ArrayList<AvaliationReturn> avaliationReturns) {
            super();
            this.avaliationReturns = avaliationReturns;
        }

        @Override
        public void run() {
            finishedLoadingOldest(avaliationReturns);
        }
    }

    protected void finishedLoadingOldest(ArrayList<AvaliationReturn> avaliationReturns) {
        appendOldest(avaliationReturns);
    }

    public void appendOldest(ArrayList<AvaliationReturn> avaliationReturns) {
        avaliacaoAdapter.addItems(avaliationReturns);
    }

}
