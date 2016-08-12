package br.com.carmaix.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.carmaix.activities.AvaliacaoActivity;
import br.com.carmaix.activities.BaseActivity;
import br.com.carmaix.R;
import br.com.carmaix.activities.DialogEsqueciSenhaActivity;
import br.com.carmaix.activities.TelaInicialActivity;
import br.com.carmaix.controller.LoginController;
import br.com.carmaix.database.DataBaseUtils;
import br.com.carmaix.exceptions.LoginFieldException;
import br.com.carmaix.exceptions.PasswordFieldException;
import br.com.carmaix.model.LoginModel;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.TokenConvertedReturn;
import br.com.carmaix.services.TokenReturn;
import br.com.carmaix.services.UserReturn;
import br.com.carmaix.utils.Constants;


/**
 * Created by root on 08/05/2016.
 */
public class LoginFragment extends BaseFragment {

    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnLogar;
    private TextView esqueciSenha;
    private LoginController controller;
    private String messageSenha;
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

        edtLogin.setText("cristian@passecarros.net");

        edtSenha = (EditText) view.findViewById(R.id.edtSenha);

        edtSenha.setText("asterix32");

        btnLogar = (Button) view.findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.setLogin(edtLogin.getText().toString());

                model.setPassword(edtSenha.getText().toString());

                try{

                    controller.validarCamposLogin();

                    runBackground("Efetuando Login...", true, true, Constants.ACTION_LOGIN);

                    /*
                    Intent i = new Intent(getActivity(), AvaliacaoActivity.class);

                    startActivity(i);

                    getActivity().finish();
                    */

                } catch (Exception ex){

                    if (ex instanceof LoginFieldException){
                        edtLogin.setError(ex.getMessage());
                    } else if (ex instanceof PasswordFieldException){
                        edtSenha.setError(ex.getMessage());
                    }

                }

            }
        });

        esqueciSenha = (TextView)view.findViewById(R.id.txt_esqueci_senha);
        String esqueciSenhaText = fragmentActivity.getResources().getString(R.string.esqueceu_senha);
        SpannableString spannableString = new SpannableString(esqueciSenhaText);
        spannableString.setSpan(new UnderlineSpan(),0,esqueciSenhaText.length(),0);
        esqueciSenha.setText(spannableString);

        esqueciSenha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(fragmentActivity, DialogEsqueciSenhaActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return view;

    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {

        if (action == Constants.ACTION_LOGIN) {

            TokenReturn token = CallService.login(fragmentActivity, model.getLogin(), model.getPassword());

            application.authorized(model.getLogin(), token);

            UserReturn user = CallService.getUser(fragmentActivity);
            TokenConvertedReturn tokenConvertedReturn = CallService.getTokenConverted(fragmentActivity);
            DataBaseUtils.includeUserData(application,user, tokenConvertedReturn, token, model.getLogin());
        }

    }

    @Override
    protected void onEndBackgroundRun(int action) {

        if (action == Constants.ACTION_LOGIN) {

            Intent i = new Intent(getActivity(), TelaInicialActivity.class);

            startActivity(i);

        }

        super.onEndBackgroundRun(action);

    }

    @Override
    protected void onBackGroundMethodException(Throwable e, boolean highPriority, int action, Object... params) {

        /*
        if (action == Constants.ACTION_SEARCH_SERVER) {
            isErrorSearchString = true;
        }
        */

        super.onBackGroundMethodException(e, highPriority, action, params);

    }



}
