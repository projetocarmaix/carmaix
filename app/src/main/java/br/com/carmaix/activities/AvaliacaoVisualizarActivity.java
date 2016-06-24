package br.com.carmaix.activities;

import android.os.Bundle;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;
import br.com.carmaix.fragments.AvaliacaoVisualizarFragment;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoVisualizarActivity extends BaseActivityHomeAsUp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_avaliacao_visualizar, new AvaliacaoVisualizarFragment()).commit();
    }
}
