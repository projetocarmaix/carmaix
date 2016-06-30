package br.com.carmaix.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import br.com.carmaix.database.DataBaseUtils;
import br.com.carmaix.database.DatabaseHelper;
import br.com.carmaix.database.LoginTable;
import br.com.carmaix.services.TokenReturn;
import br.com.carmaix.utils.Utils;

/**
 * Created by root on 01/05/2016.
 */
public class ApplicationCarmaix extends Application {

    private Authentication authHelper;

    private DatabaseHelper databaseHelper = null;

    private LoginTable loginTable;



    @Override
    public void onCreate() {

        super.onCreate();

        Utils.setContext(this);

        getHelper();

        authHelper = new Authentication(this, this);

    }

    public DatabaseHelper getHelper() {

        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }

        return databaseHelper;

    }

    public void setHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public boolean isAuthorized() {
        return authHelper.hasAccessToken();
    }

    public LoginTable getLoginTable() {

        try {

            if (loginTable == null) {

                loginTable = DataBaseUtils.getLoginTable(this);

            }

            return loginTable;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public void setLoginTable(LoginTable loginTable) {
        this.loginTable = loginTable;
    }

    public void authorized(String userName, TokenReturn tokenReturn) throws Exception {

        try {
            getTokenAndCreateAccount(userName, tokenReturn);
        } catch (Exception e) {
            throw e;
        }

    }

    public void getTokenAndCreateAccount(String userName, TokenReturn tokenReturn) throws Exception {

        try {

            authHelper.createAccountWithToken(userName, tokenReturn);

        } catch (Exception e) {
            throw e;
        }

    }

    public Authentication getAuthHelper() {
        return authHelper;
    }

}
