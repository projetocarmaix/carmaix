package br.com.carmaix.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import br.com.carmaix.R;
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
        String avaliacaoId = "237072";
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
}
