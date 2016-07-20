package br.com.carmaix.services;

import android.content.Context;

import java.util.ArrayList;

import br.com.carmaix.utils.ValueLabelDefault;

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


    public static UserReturn getUser(Context context) throws Exception{
        return getVersionRelease().getUser(context);
    }


    public static ArrayList<AvaliationReturn> listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{

        return getVersionRelease().listAvaliations(context, methodType, limit, offset, status, sortBy, sortOrder);
    }

    public static ArrayList<AvaliationReturn> searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{
        return getVersionRelease().searchAvaliations(context, methodType, pattern, limit, offset, status, sortBy, sortOrder);
    }

    public static String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception{
        return getVersionRelease().getAvaliationsDetail(context, methodType, id);
    }

    public static TokenConvertedReturn getTokenConverted(Context context) throws Exception{
        return getVersionRelease().getTokenConverted(context);
    }

    public static ArrayList<ValueLabelDefault> listVendedor(Context context) throws Exception {
        return getVersionRelease().listVendedor(context);
    }

    public static ArrayList<ValueLabelDefault> listCategorias(Context context) throws Exception {
        return getVersionRelease().listCategorias(context);
    }

    public static ArrayList<ValueLabelDefault> listCombustiveis(Context context) throws Exception {
        return getVersionRelease().listCombustiveis(context);
    }

    public static ArrayList<ValueLabelDefault> listMarcasCategoria(Context context, String id) throws Exception {
        return getVersionRelease().listMarcasCategoria(context, id);
    }

}