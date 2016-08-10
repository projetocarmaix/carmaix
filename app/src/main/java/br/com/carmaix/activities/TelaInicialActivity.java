package br.com.carmaix.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.table.TableUtils;

import java.util.Collection;
import java.util.List;

import br.com.carmaix.R;
import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.services.CallService;
import br.com.carmaix.services.ListAvaliationReturn;
import br.com.carmaix.services.MethodType;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;

public class TelaInicialActivity extends ParentBaseActivity {

    protected Handler handler = new Handler();

    private boolean authenticated = false;

    private ApplicationCarmaix application;

    private Bundle bundle = null;
    private Bundle bundleServerSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.application = (ApplicationCarmaix) getApplication();

        if(this.application != null) {
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.layout_start_app);

        if (getIntent() != null) {
            bundle = getIntent().getExtras();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        verifyConnection(true);

    }

    private void beginAuthorization() {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

    }

    @Override
    protected void backgroundMethod(int action) throws Throwable {

        if (action == Constants.ACTION_LOGOFF) {

            /*
            try {

                Collection<ServerInformation> listTenantServerReturns = null;

                listTenantServerReturns = DataBaseUtils.getListTenants(TelaInicialActivity.this, MethodType.CACHE_YES);

                try {

                    LogoffServersAsyncTask logoffServersAsyncTask = new LogoffServersAsyncTask(this,
                            listTenantServerReturns);
                    logoffServersAsyncTask.execute();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                application.getAuthHelper().logoff(true);

                application.setServerLoggued(null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            */

            bundle = null;

        }

    }

    @Override
    protected void onEndBackgroundRun(int action) {

        if (action == Constants.ACTION_LOGOFF) {
            verifyConnection(true);
        }
    }

    @Override
    protected void onBackGroundMethodException(Throwable e, boolean highPriority) {
    }

    @Override
    protected void onDestroy() {

        if (application.getHelper() != null) {
            OpenHelperManager.releaseHelper();
            application.setHelper(null);
        }

        super.onDestroy();
    }

    private void verifyConnection(boolean validateFragment) {
        if (bundle != null && bundle.get(Constants.LOGOFF) != null) {
            Utils.alertToast("", this.getString(R.string.loadingEmpty), this, Toast.LENGTH_SHORT);

            runBackground("Carregando...", false, true, Constants.ACTION_LOGOFF);

        } else {
            try {
                if (!application.isAuthorized()) {
                    beginAuthorization();
                } else {

                    authenticated = true;

                    bundleServerSelected = null;
                    bundle = null;

                    application.getAuthHelper().loadAccessToken();

                    application.getAuthHelper().loadDataLogin();

                    Intent i = new Intent(this, AvaliacaoActivity.class);
                    startActivity(i);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

}
