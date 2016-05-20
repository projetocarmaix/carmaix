package br.com.carmaix.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.carmaix.MainActivity;
import br.com.carmaix.R;
import br.com.carmaix.controller.LoginController;
import br.com.carmaix.exceptions.LoginFieldException;
import br.com.carmaix.exceptions.PasswordFieldException;
import br.com.carmaix.model.LoginModel;


/**
 * Created by root on 08/05/2016.
 */
public class LoginFragment extends Fragment {

    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnLogar;

    private LoginController controller;

    private LoginModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        model = new LoginModel();

        controller = new LoginController(getActivity(), model, this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, container, false);

        edtLogin = (EditText) view.findViewById(R.id.edtLogin);
        edtSenha = (EditText) view.findViewById(R.id.edtSenha);

        btnLogar = (Button) view.findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.setLogin(edtLogin.getText().toString());

                model.setPassword(edtSenha.getText().toString());

                try{

                    controller.validarCamposLogin();

                    Intent i = new Intent(getActivity(), MainActivity.class);

                    startActivity(i);

                    getActivity().finish();

                } catch (Exception ex){

                    if (ex instanceof LoginFieldException){
                        edtLogin.setError(ex.getMessage());
                    } else if (ex instanceof PasswordFieldException){
                        edtSenha.setError(ex.getMessage());
                    }

                }

            }
        });

        return view;

    }


}
