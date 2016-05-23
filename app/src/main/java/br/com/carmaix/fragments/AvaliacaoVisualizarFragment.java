package br.com.carmaix.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.carmaix.R;

/**
 * Created by fernando on 23/05/16.
 */
public class AvaliacaoVisualizarFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.avaliacao_visualizar_fragment,container,false);
        return v;
    }
}
