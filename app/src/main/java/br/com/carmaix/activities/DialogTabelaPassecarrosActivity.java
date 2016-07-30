package br.com.carmaix.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import br.com.carmaix.R;
import br.com.carmaix.fragments.DialogTabelaPassecarrosFragment;

/**
 * Created by fernando on 29/07/16.
 */
public class DialogTabelaPassecarrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_tabela_passecarros_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_dialog, new DialogTabelaPassecarrosFragment()).commit();
    }
}
