package br.com.carmaix.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.style.EasyEditSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.activities.DialogEsqueciSenhaActivity;
import br.com.carmaix.activities.LoginActivity;
import br.com.carmaix.services.AnosCombustivelReturn;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.CategoriaReturn;
import br.com.carmaix.services.CombustiveisModelosReturn;
import br.com.carmaix.services.MarcasCategoriaReturn;
import br.com.carmaix.services.ModelosMarcaReturn;
import br.com.carmaix.services.ValorMedioReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;
import br.com.carmaix.utils.ValueLabelDefault;

/**
 * Created by fernando on 21/07/16.
 */
public class DialogEsqueciSenhaFragment extends BaseFragment {
    private View view;
    private EditText editTextEsqueciSenha;
    private Button buttonEsqueciSenha;
    private ProgressBar progressBarEsqueciSenha;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_esqueci_senha,container,false);
        editTextEsqueciSenha = (EditText)view.findViewById(R.id.edit_esqueci_senha);
        buttonEsqueciSenha = (Button)view.findViewById(R.id.btn_esqueci_senha);
        progressBarEsqueciSenha = (ProgressBar)view.findViewById(R.id.progressbar_esqueci_senha);

        buttonEsqueciSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String email = editTextEsqueciSenha.getText().toString();
                AsyncTask asyncTask = new AsyncTask() {

                    @Override
                    protected void onPreExecute() {
                        progressBarEsqueciSenha.setVisibility(View.VISIBLE);
                        buttonEsqueciSenha.setVisibility(View.GONE);
                    }

                    @Override
                    protected Object doInBackground(Object[] objects) {
                        String messageReturn = "";
                        try {
                            messageReturn = CallService.sendEsqueciSenha(fragmentActivity,email);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return messageReturn;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        progressBarEsqueciSenha.setVisibility(View.GONE);
                        buttonEsqueciSenha.setVisibility(View.VISIBLE);

                        String messageToShow = o.toString();
                        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(fragmentActivity);
                        final Boolean error = messageToShow.isEmpty();
                        if(error) {
                            alerBuilder.setTitle(fragmentActivity.getResources().getString(R.string.algo_deu_errado));
                            messageToShow = fragmentActivity.getResources().getString(R.string.nao_foi_possivel_enviar_email);
                        }else {
                            alerBuilder.setTitle(fragmentActivity.getResources().getString(R.string.sucesso));
                        }
                        final AlertDialog.Builder alerBuilderFinal = alerBuilder;
                        alerBuilder.setMessage(messageToShow);
                        alerBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(error) {
                                    dialogInterface.cancel();
                                }else {
                                    Intent intent = new Intent(fragmentActivity, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(intent);
                                }
                            }
                        });
                        alerBuilder.show();
                    }
                };

                asyncTask.execute();
            }


        });

        return view;
    }

}

