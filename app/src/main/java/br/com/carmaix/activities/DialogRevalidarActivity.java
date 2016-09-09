package br.com.carmaix.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import br.com.carmaix.R;
import br.com.carmaix.fragments.DialogAlterarSenhaFragment;
import br.com.carmaix.fragments.DialogRevalidarFragment;
import br.com.carmaix.utils.Constants;

/**
 * Created by fernando on 29/07/16.
 */
public class DialogRevalidarActivity extends AppCompatActivity {
    private String avaliacaoId;
    private String vendedorId;
    private String modelo;
    private String marca;
    private String placa;
    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        avaliacaoId = extras.getString("avaliacaoId");
        vendedorId = extras.getString("vendedorId");
        modelo = extras.getString("modelo");
        marca = extras.getString("marca");
        placa = extras.getString("placa");
        action = extras.getInt("action");

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_container);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_dialog, new DialogRevalidarFragment()).commit();
    }

    public String getAvaliacaoId() {
        return avaliacaoId;
    }

    public String getVendedorId() {
        return vendedorId;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAction() {
        return action;
    }
}
