package br.com.carmaix.fragments;

import android.app.SearchManager;
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
import android.widget.TextView;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliacaoVisualizarActivity;
import br.com.carmaix.adapters.AvaliacaoAdapter;
import br.com.carmaix.model.Model;
import br.com.carmaix.services.AvaliationReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.MethodType;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.view.EmptyRecyclerView;

/**
 * Created by fernando on 21/05/16.
 */
public class SearchAvaliationFragment extends BaseFragment {

    private String query = "";

    private LinearLayoutManager linearLayoutManager;

    private ArrayList<AvaliationReturn> avaliationReturns;

    private EmptyRecyclerView recyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private View layoutEmpty = null;

    private TextView emptyTextView = null;

    private ProgressBar mProgressBar;

    private AvaliacaoAdapter avaliacaoAdapter = new AvaliacaoAdapter(fragmentActivity, new ArrayList<AvaliationReturn>());

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.i("zzz", "zzz " + Constants.typeStatus);

        /*
        Intent intent = fragmentActivity.getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            query = intent.getStringExtra(SearchManager.QUERY);

        }
        */

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.avaliacao_fragment, container, false);

        recyclerView = (EmptyRecyclerView) view.findViewById(R.id.avaliacaoRecyclerView);

        layoutEmpty = view.findViewById(R.id.item_empty_list);

        emptyTextView = (TextView) view.findViewById(R.id.textEmpty);

        recyclerView.setEmptyView(layoutEmpty);

        emptyTextView.setText(fragmentActivity.getString(R.string.loadingEmpty));

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);

        mSwipeRefreshLayout.setEnabled(false);

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
            }

        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {}

        });

        registerForContextMenu(recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent i = new Intent(getContext(), AvaliacaoVisualizarActivity.class);
        startActivity(i);
        return true;
    }

    @Override
    protected void backgroundMethod(int action, Object... params) throws Throwable {

        if (action == Constants.ACTION_SEARCH){

            String query = (String) params[0];

            ArrayList<AvaliationReturn> avaliationReturns = CallService.searchAvaliations(fragmentActivity, MethodType.CACHE_NO, query, Constants.MAX_ITEMS, 0, Constants.typeStatus, "", "");

            this.avaliationReturns = avaliationReturns;

        }

        super.backgroundMethod(action, params);

    }

    @Override
    protected void onEndBackgroundRun(int action, Object... params) {

        if (action == Constants.ACTION_SEARCH) {

            endBackGroundList();

            if (avaliacaoAdapter == null || avaliacaoAdapter.getItemCount() == 0) {
                emptyTextView.setText(fragmentActivity.getString(R.string.emptyValues));
            }

            hideProgressBar();

        }

        super.onEndBackgroundRun(action);

    }

    @Override
    protected void onBackGroundMethodException(Throwable e, boolean highPriority, int action, Object... params) {

        e.printStackTrace();

        hideProgressBar();

        emptyTextView.setText(fragmentActivity.getString(R.string.errorServer));

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

    public void search(String query) {

        showProgressBar();

        if (emptyTextView != null){
            emptyTextView.setText(fragmentActivity.getString(R.string.loadingEmpty));
        }

        runBackgroundParams("", false, true, Constants.ACTION_SEARCH, query);

    }
}
