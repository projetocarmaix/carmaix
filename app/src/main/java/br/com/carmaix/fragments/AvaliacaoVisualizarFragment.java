package br.com.carmaix.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import br.com.carmaix.R;
import br.com.carmaix.activities.AvaliacaoVisualizarActivity;
import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 23/05/16.
 */
public class AvaliacaoVisualizarFragment extends BaseFragment {
    private WebView webView;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avaliacao_visualizar_fragment,container,false);
        webView = (WebView)view.findViewById(R.id.avaliacao_visualizar_webview);
        progressBar = (ProgressBar)view.findViewById(R.id.avaliacao_visualizar_progress_bar);
        String avaliacaoId = ((AvaliacaoVisualizarActivity)getActivity()).getAvaliacaoId();
        setWebViewClient();

        ApplicationCarmaix application = (ApplicationCarmaix) getActivity().getApplicationContext();

        Map<String,String> header = new HashMap<>();
        header.put("Authorization","Bearer " + application.getLoginTable().getToken());
        webView.loadUrl(Constants.URL_VISUALIZAR_AVALIACAO+"/"+avaliacaoId+"/checklist", header);
        return view;
    }

    private void setWebViewClient() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_visualizar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_print:
                createWebPrintJob();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createWebPrintJob() {

        PrintManager printManager = (PrintManager) fragmentActivity.getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        String jobName = getString(R.string.app_name) + " Document";

        printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }
}
