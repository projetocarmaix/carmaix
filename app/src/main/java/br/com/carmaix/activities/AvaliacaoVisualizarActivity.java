package br.com.carmaix.activities;

import android.os.Bundle;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;
import br.com.carmaix.fragments.AvaliacaoVisualizarFragment;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoVisualizarActivity extends BaseActivityHomeAsUp {
    private String avaliacaoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            avaliacaoId = extras.getString("avaliacaoId");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_home_as_up, new AvaliacaoVisualizarFragment()).commit();
    }

    public String getAvaliacaoId() {
        return avaliacaoId;
    }

}
