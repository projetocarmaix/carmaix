package br.com.carmaix.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by rafael.savaris on 04/05/2016.
 */
public class Utils {

    private static Context contextApplication = null;

    public static boolean isConnected(Context context) {

        try {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String verifyResponseCode(Context context, HttpResponse response, Boolean catchErrorECM,
                                            boolean convertResponse, boolean useGZip, boolean isIgnoreNewResult) throws Exception {

        int responseCode = response.getStatusLine().getStatusCode();

        String message = response.getStatusLine().getReasonPhrase();

        Log.d("ResponseMessage", message);

        switch (responseCode) {

            case 200:

                if (convertResponse) {
                    return convertStreamToString(response, useGZip);
                } else {
                    return "";
                }

            default:
                throw new Exception("Erro na execução do rest " + responseCode + " " + message);
        }

    }

    private static String convertStreamToString(final HttpResponse response, boolean useGZip) throws IOException {

        String result;

        InputStreamReader inputContent;

        if (useGZip){

            HttpEntity a = response.getEntity();

            inputContent = new InputStreamReader(AndroidHttpClient.getUngzippedContent(a));

        } else {
            inputContent = new InputStreamReader(response.getEntity().getContent());
        }

        BufferedReader bufferedReader = new BufferedReader(inputContent, 8 * 1024);

        StringBuffer stringBuffer = new StringBuffer("");

        String line = "";

        String NL = System.getProperty("line.separator");

        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line + NL);
        }

        bufferedReader.close();

        result = stringBuffer.toString();

        return result;

    }

    public static boolean isExternalStorageWritable() {

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }

        return false;
    }

    public static void setContext(Context context) {
        if (Utils.contextApplication == null) {
            Utils.contextApplication = context;
        }

    }

    public static Context getContextApplication() {
        return Utils.contextApplication;
    }

    public static void alertToast(String title, String message, Context context) {
        alertToast(title, message, context, Toast.LENGTH_LONG);
    }

    public static void alertToast(String title, String message, Context context, int duration) {
        if (!title.equals("")) {
            title += ": ";
        }
        CharSequence text = title + message;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static ArrayList<ValueLabelDefault> createArrayDefault(Object object) {
        ArrayList<ValueLabelDefault> arraylistValueLabelDefault = new ArrayList<>();
        if(object instanceof ValueLabelDefault) {
            String valueDefault = ((ValueLabelDefault) object).getValueDefault();
            ((ValueLabelDefault) object).setId("");
            ((ValueLabelDefault) object).setDescricao(valueDefault);

            arraylistValueLabelDefault.add(((ValueLabelDefault) object));
        }
        return arraylistValueLabelDefault;
    }

}
