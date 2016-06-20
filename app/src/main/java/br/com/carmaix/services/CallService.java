package br.com.carmaix.services;

import android.content.Context;

import java.util.ArrayList;

public class CallService {

    public static String getVersionReleaseServerLogged() {
        return "1.0";
    }

    public static VersionRelease getVersionRelease() {
        return getVersionRelease(getVersionReleaseServerLogged());
    }

    private static VersionRelease getVersionRelease(String versionRequired) {

        if (versionRequired == null || versionRequired.equals("")) {
            return getVersionRelease();
        } else {

            if (versionRequired.startsWith("1.0")) {
                return new ServiceDefault();
            }

            return new ServiceDefault();

        }

    }

    public static TokenReturn login(Context context, String user, String password) throws Exception{
        return getVersionRelease().login(context, user, password);
    }

    public static ArrayList<AvaliationReturn> listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{
        return getVersionRelease().listAvaliations(context, methodType, limit, offset, status, sortBy, sortOrder);
    }

    public static AvaliationReturn searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{
        return getVersionRelease().searchAvaliations(context, methodType, pattern, limit, offset, status, sortBy, sortOrder);
    }

    public static String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception{
        return getVersionRelease().getAvaliationsDetail(context, methodType, id);
    }

}