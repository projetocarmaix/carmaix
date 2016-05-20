package br.com.carmaix.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.carmaix.R;
import br.com.carmaix.fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private LoginFragment fragment;

    /*
    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnLogar;

    private SharedPreferences preferences;

    private LoginBO loginBO;

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.fragment = new LoginFragment();

        setContentView(R.layout.default_activity);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, this.fragment).commit();

        getSupportActionBar().hide();

        /*

        loginBO = new LoginBO();

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String login = preferences.getString("login", null);
        String senha = preferences.getString("senha", null);

        if (login != null && senha != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnLogar = (Button) findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivity.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtSenha(edtSenha);
                validation.setLogin(login);
                validation.setSenha(senha);

                boolean isValido = loginBO.validarCamposLogin(validation);
                if (isValido) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        */

    }


}
