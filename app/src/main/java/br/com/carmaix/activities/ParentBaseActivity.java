package br.com.carmaix.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by root on 12/06/2016.
 */
public class ParentBaseActivity extends AppCompatActivity {


    private boolean showDialog = true;

    private Dialog dialog = null;

    private Dialog progressDialog = null;

    private String result = null;

    final Handler mHandler = new Handler();

    private Throwable exception = null;

    final class mExceptionResults implements Runnable {

        private final Throwable exception;

        private final boolean highPriority;

        public mExceptionResults(Throwable exception, boolean highPriority) {
            super();
            this.exception = exception;
            this.highPriority = highPriority;

        }

        @Override
        public void run() {
            if (showDialog)
                progressDialog.hide();

            if (this.exception != null)
                this.exception.printStackTrace();
            onBackGroundMethodException(this.exception, this.highPriority);

        }

    }

    final class mUpdateResults implements Runnable {

        private final int action;

        public mUpdateResults(int action) {
            super();
            this.action = action;
        }

        @Override
        public void run() {
            onRunFinish(action);
        }
    };

    protected void setMessage(String message) {
        this.result = message;
    }

    protected String getMessage() {
        return this.result;
    }

    protected void onRunFinish(int action) {
        if (showDialog)
            progressDialog.hide();
        if (!"".equals(result)) {
            getDialog().show();
        }
        onEndBackgroundRun(action);
    }

    protected Dialog getDialog() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this).setMessage(result).setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    }).create();
        }
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (dialog != null)
            dialog.dismiss();
        if (progressDialog != null)
            progressDialog.dismiss();
        super.onDestroy();
    }

    final public void runBackground(final String message, final boolean showDialog, final boolean highPriority,
                                    final int action) {
        this.showDialog = showDialog;
        if (showDialog) {
            if (progressDialog == null)
                progressDialog = ProgressDialog.show(this, "", message, true);
            progressDialog.show();
        }
        result = "";
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    backgroundMethod(action);
                    mHandler.post(new mUpdateResults(action));
                } catch (Throwable e) {
                    exception = e;
                    mHandler.post(new mExceptionResults(exception, highPriority));
                }
                // mHandler.post(new mUpdateResults(action));
            }
        };

        t.start();
    }

    protected void backgroundMethod(int action) throws Throwable {}

    protected void onEndBackgroundRun(int action) {}

    protected void onBackGroundMethodException(Throwable e, boolean highPriority) {}

}
