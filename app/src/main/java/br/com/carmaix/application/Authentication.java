package br.com.carmaix.application;

import android.content.Context;

import java.security.KeyException;

import br.com.carmaix.R;
import br.com.carmaix.database.DataBaseUtils;
import br.com.carmaix.database.LoginTable;
import br.com.carmaix.services.TokenReturn;

public class Authentication {

    private ApplicationCarmaix application;

    private Context context;

    private boolean authenticated = false;

    private String tokenaccess;

    public Authentication(Context context, ApplicationCarmaix application) {

        this.application = application;
        this.context = context;

        authenticated = loadAccessToken();

    }


    public boolean hasAccessToken() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public void createAccountWithToken(String userName, TokenReturn tokenReturn) throws Exception {

        try {

            DataBaseUtils.createAccount(application, userName, tokenReturn);

            authenticated = true;

        } catch (Exception e) {
            throw new Exception("Não foi possível cadastrar conta.");
        }

    }

    public boolean loadAccessToken() {

        try {

            LoginTable loginTable = DataBaseUtils.getLoginTable(application);

            if (loginTable != null) {

                application.setLoginTable(loginTable);

                String token = loginTable.getToken();

                if (null != token) {
                    tokenaccess = token;
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;

    }

    public void logoff(Boolean eraseServers) throws Exception {

        /*

        authenticated = false;

        CacheManager.clearAllCache(new ConsumerSDK(context));

        if (eraseServers) {
            DataBaseUtils.deleteAllServers(application);

            try{
                application.getDatabaseHelper().deleteAllDatasetsStrucure(application);
            } catch (Exception ex){
                ex.printStackTrace();
            }

        }

        */

    }

    public String getTokenaccess() {
        return tokenaccess;
    }

    public void setTokenaccess(String tokenaccess) {
        this.tokenaccess = tokenaccess;
    }

    /*
    public void storeDataLogin(int serverId, Boolean changeServer, String userID, String userAlias, String tenantId,
                               String serverVersion) throws Exception {

        try {
            DataBaseUtils.updateTenantServer(application, serverId, changeServer, userID, userAlias, tenantId,
                    serverVersion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    */

    public void loadDataLogin() {

        try {

            LoginTable loginTable = DataBaseUtils.getLoginTable(application);

            if (loginTable != null) {
               application.setLoginTable(loginTable);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
