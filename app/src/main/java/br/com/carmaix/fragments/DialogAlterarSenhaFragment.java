package br.com.carmaix.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import br.com.carmaix.R;
import br.com.carmaix.activities.LoginActivity;
import br.com.carmaix.services.CallService;
import br.com.carmaix.utils.Constants;
import eu.inmite.android.lib.validations.form.annotations.RegExp;

/**
 * Created by fernando on 21/07/16.
 */
public class DialogAlterarSenhaFragment extends BaseFragment {
    private View view;
    private ProgressBar progressBarAlterarSenha;
    private EditText senhaAtual;
    private EditText novaSenha;
    private EditText confirmacaoNovaSenha;
    private Button alterarSenha;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_alterar_senha,container,false);
        progressBarAlterarSenha = (ProgressBar)view.findViewById(R.id.progressbar_alterar_senha);

        senhaAtual = (EditText)view.findViewById(R.id.senha_atual);
        novaSenha = (EditText)view.findViewById(R.id.nova_senha);
        confirmacaoNovaSenha = (EditText)view.findViewById(R.id.confirmacao_senha);
        alterarSenha = (Button)view.findViewById(R.id.btn_alterar_senha);
        final String atual =senhaAtual.getText().toString();
        final String nova = novaSenha.getText().toString();
        final String confirmacao = confirmacaoNovaSenha.getText().toString();


        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTask asyncTask = new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        try {
                            CallService.alterarSenha(fragmentActivity,atual,nova,confirmacao);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {

                    }
                };

                asyncTask.execute();



            }
        });

        return view;
    }


}

