package br.com.carmaix.services;

import android.content.Context;

import org.json.JSONException;

import java.util.ArrayList;

import br.com.carmaix.utils.ValueLabelDefault;

public interface VersionRelease {

    TokenReturn login(Context context, String user, String password) throws Exception;

    ArrayList<AvaliationReturn> listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception;

    ArrayList<AvaliationReturn> searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception;

    String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception;

    UserReturn getUser(Context context) throws Exception;

    TokenConvertedReturn getTokenConverted(Context context) throws JSONException;

    ArrayList<ValueLabelDefault> listVendedor(Context context) throws Exception;

    ArrayList<ValueLabelDefault> listCategorias(Context context) throws Exception;

    ArrayList<ValueLabelDefault> listCombustiveis(Context context) throws Exception;

    ArrayList<ValueLabelDefault> listMarcasCategoria(Context context, String id) throws Exception;

    ArrayList<ValueLabelDefault> listModelosMarca(Context context, String id) throws Exception;

    ArrayList<ValueLabelDefault> listAnoFabricacao(Context context, String id) throws Exception;

    ArrayList<ValueLabelDefault> listAnoModelo(Context context, String id, String ano) throws Exception;

    ArrayList<ValueLabelDefault> listOpcionais(Context context) throws Exception;
}
