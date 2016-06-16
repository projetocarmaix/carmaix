package br.com.carmaix.activities;

import android.os.Bundle;
import br.com.carmaix.R;
import br.com.carmaix.fragments.AvaliacaoFragment;
import br.com.carmaix.fragments.AvaliacaoFragmentTab;

/**
 * Created by fernando on 21/05/16.
 */
public class AvaliacaoActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*commit da feature teste*/
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new AvaliacaoFragmentTab()).commit();
    }


}
