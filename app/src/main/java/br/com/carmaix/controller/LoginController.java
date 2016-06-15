package br.com.carmaix.controller;

import android.content.Context;
import android.support.v4.app.Fragment;

import br.com.carmaix.exceptions.LoginFieldException;
import br.com.carmaix.exceptions.PasswordFieldException;
import br.com.carmaix.model.LoginModel;


/**
 * Created by root on 08/05/2016.
 */
public class LoginController {

    private Context context;
    private LoginModel model;
    private Fragment fragment;

    public LoginController(Context context, LoginModel model, Fragment fragment) {
        this.context = context;
        this.model = model;
        this.fragment = fragment;
    }

    public void validarCamposLogin() throws Exception{

        if (model.getLogin() == null || "".equals(model.getLogin())) {
            throw new LoginFieldException("Campo obrigatório!");
        } else if (model.getLogin().length() < 2) {
            throw new LoginFieldException("Campo deve ter no mínimo 3 caracteres!");
        }

        if (model.getPassword() == null || "".equals(model.getPassword())) {
            throw new PasswordFieldException("Campo obrigatório!");
        }

    }


}
