package br.com.carmaix.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rafael.savaris on 04/05/2016.
 */
public class Utils {

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

}
