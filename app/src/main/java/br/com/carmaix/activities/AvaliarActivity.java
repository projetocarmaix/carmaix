package br.com.carmaix.activities;

import android.os.Bundle;

import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliarFragmentTab;

/**
 * Created by fernando on 07/07/16.
 */
public class AvaliarActivity extends BaseActivityHomeAsUp{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_home_as_up, new AvaliarFragmentTab()).commit();
    }
}
