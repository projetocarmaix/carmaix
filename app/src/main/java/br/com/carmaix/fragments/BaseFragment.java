package br.com.carmaix.fragments;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.utils.Utils;

/**
 * Created by fernando on 21/05/16.
 */
public class BaseFragment extends Fragment{

    protected ApplicationCarmaix application;
    protected FragmentActivity fragmentActivity;
    protected Handler handler = new Handler();

    private DialogFragment progressDialog = null;

    private Throwable exception = null;

    final class mExceptionResults implements Runnable {

        private final Throwable exception;
        private final boolean highPriority;
        private final int action;
        private final Object[] objects;
        private final boolean validateDetach;
        private final boolean showDialog;

        public mExceptionResults(Throwable exception, boolean highPriority, int action, Object[] objects,
                                 boolean validateDetach, boolean showDialog) {
            super();
            this.exception = exception;
            this.highPriority = highPriority;
            this.action = action;
            this.objects = objects;
            this.validateDetach = validateDetach;
            this.showDialog = showDialog;
        }

        @Override
        public void run() {

            try {

                if (showDialog) {
                    progressDialog.getDialog().hide();
                    progressDialog.dismiss();
                    progressDialog = null;
                }

            } catch (Throwable e) {
                Log.e("ERROR LOADING", (e.getMessage() != null ? e.getMessage() : "Erro ao cancelar Loading"));
            }

            if (exception != null) {
                exception.printStackTrace();
            }

            if (validateDetach) {

                if (isDetached()) {
                    return;
                }

            }

            try {
                onBackGroundMethodException(exception, highPriority, action, objects);
            } catch (RuntimeException re) {
                re.printStackTrace();
            }

        }

    }

    final class mUpdateResults implements Runnable {

        private final int action;
        private final boolean validateDetach;
        private final boolean showDialog;

        public mUpdateResults(int action, boolean validateDetach, boolean showDialog) {
            super();
            this.action = action;
            this.validateDetach = validateDetach;
            this.showDialog = showDialog;
        }

        @Override
        public void run() {

            if (validateDetach) {

                if (isDetached()) {
                    return;
                }

            }

            try {
                onRunFinish(action, showDialog);
            } catch (Throwable re) {
                re.printStackTrace();
            }

        }
    };

    final class mUpdateResultsParams implements Runnable {

        private final int action;
        private final boolean validateDetach;
        private final Object[] objects;
        private final boolean showDialog;

        public mUpdateResultsParams(int action, boolean validateDetach, boolean showDialog, Object[] objects) {
            super();
            this.action = action;
            this.validateDetach = validateDetach;
            this.objects = objects;
            this.showDialog = showDialog;
        }

        @Override
        public void run() {

            if (validateDetach) {

                if (isDetached()) {
                    return;
                }

            }

            try {
                onRunFinish(action, showDialog, objects);
            } catch (Throwable re) {
                re.printStackTrace();
            }
        }
    };

    protected void onRunFinish(int action, boolean showDialog) {

        try {

            if (showDialog) {
                progressDialog.getDialog().hide();
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Throwable e) {
            Log.e("ERROR LOADING", (e.getMessage() != null ? e.getMessage() : "Erro ao cancelar Loading"));
        }

        onEndBackgroundRun(action);

    }

    protected void onRunFinish(int action, boolean showDialog, Object... params) {

        try {

            if (showDialog) {
                progressDialog.getDialog().hide();
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Throwable e) {
            Log.e("ERROR LOADING", (e.getMessage() != null ? e.getMessage() : "Erro ao cancelar Loading"));
        }

        onEndBackgroundRun(action, params);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        fragmentActivity = getActivity();

        application = (ApplicationCarmaix) fragmentActivity.getApplication();

    }

    @Override
    public void onDestroy() {

        try {

            if (progressDialog != null) {
                progressDialog.getDialog().hide();
                progressDialog.dismiss();
                progressDialog = null;
            }

        } catch (Throwable e) {
            Log.e("ERROR LOADING", (e.getMessage() != null ? e.getMessage() : "Erro ao cancelar Loading"));
        }

        super.onDestroy();
    }

    final public void runBackground(final String message, final boolean showDialog, final boolean highPriority,
                                    final int action) {
        runBackground(message, showDialog, highPriority, action, true);
    }

    final public void runBackground(final String message, final boolean showDialog, final boolean highPriority,
                                    final int action, final boolean validateDetach) {

        if (showDialog) {
            if (progressDialog == null) {
                progressDialog = MyAlertDialogFragment.newInstance("", message);
            }
            progressDialog.setCancelable(false);
            progressDialog.show(getFragmentManager(), "");
        }
        Thread t = new Thread() {
            @Override
            public void run() {
                try {

                    if (!isDaemon()) {
                        backgroundMethod(action);
                    }
                    handler.post(new mUpdateResults(action, validateDetach, showDialog));
                } catch (Throwable e) {
                    exception = e;
                    handler.post(new mExceptionResults(exception, highPriority, action, null, validateDetach,
                            showDialog));
                }
            }
        };

        t.start();
    }

    final public void runBackgroundParams(final String message, final boolean showDialog, final boolean highPriority,
                                          final int action, final Object... params) {
        runBackgroundParams(true, message, showDialog, highPriority, action, params);
    }

    final public void runBackgroundParams(final boolean validateDetach, final String message, final boolean showDialog,
                                          final boolean highPriority, final int action, final Object... params) {

        if (showDialog) {
            if (progressDialog == null) {
                progressDialog = MyAlertDialogFragment.newInstance("", message);
            }
            progressDialog.setCancelable(false);
            progressDialog.show(getFragmentManager(), "");
        }
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    if (!isDaemon()) {
                        backgroundMethod(action, params);
                    }
                    handler.post(new mUpdateResultsParams(action, validateDetach, showDialog, params));
                } catch (Throwable e) {
                    exception = e;
                    handler.post(new mExceptionResults(exception, highPriority, action, params, validateDetach,
                            showDialog));
                }
            }
        };

        t.start();
    }

    /**
     * Background method.
     *
     * @param action
     *            the action
     * @throws Exception
     *             the exception
     */
    protected void backgroundMethod(int action) throws Throwable {}

    protected void backgroundMethod(int action, Object... params) throws Throwable {}

    /**
     * On end background run.
     *
     * @param action
     *            the action
     */
    protected void onEndBackgroundRun(int action) {}

    protected void onEndBackgroundRun(int action, Object... params) {}

    protected void onBackGroundMethodException(Throwable e, boolean highPriority, int action, Object... params) {
        Utils.alertToast("", e.getMessage(), fragmentActivity);
    }

    public static class MyAlertDialogFragment extends DialogFragment {

        public static MyAlertDialogFragment newInstance(String title, String message) {

            MyAlertDialogFragment frag = new MyAlertDialogFragment();
            Bundle args = new Bundle();

            args.putString("title", title);
            args.putString("message", message);

            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setTitle(getArguments().getString("title"));
            dialog.setMessage(getArguments().getString("message"));
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            return dialog;

        }

    }

    public void setEmptyText(String message) {}

    public void catchSpecificError(Throwable e) {}

}
