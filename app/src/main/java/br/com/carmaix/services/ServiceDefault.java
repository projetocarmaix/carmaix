package br.com.carmaix.services;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.carmaix.R;
import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.cache.CacheManager;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;

public class ServiceDefault implements VersionRelease {

    public TokenReturn login(Context context, String user, String password) throws Exception{

        TokenReturn tokenReturn = null;

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/auth/login";

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET_AND_POST);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);

        consumerSDK.AddParam("username", user);
        consumerSDK.AddParam("password", password);

        textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);

        if (textJson != null) {

            Gson gson = new Gson();

            JsonParser parser = new JsonParser();

            JsonElement element;

            try {
                element = parser.parse(textJson);
            } catch (Exception e) {

                CacheManager.invalidateCache(consumerSDK);
                Log.e("Service_1_3", "getLoggedUserCache JSON Error: " + e.getMessage());
                throw new Exception(Utils.getContextApplication().getString(R.string.errorMessage500));

            }

            tokenReturn = gson.fromJson(element, TokenReturn.class);

        }
        return tokenReturn;

    }

    public ArrayList<AvaliationReturn> listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{

        ArrayList<AvaliationReturn> avaliationReturns = new ArrayList<AvaliationReturn>();

        ListAvaliationReturn listAvaliationReturn = null;

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes";

        RestSKD consumerSDK = new RestSKD(context);

        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);

        consumerSDK.AddParam("limit", ""+limit);

        consumerSDK.AddParam("offset", ""+offset);

        if (status != null && !status.equals("")){
            consumerSDK.AddParam("s", ""+status);
        }

        if (sortBy != null && !sortBy.equals("")){
            consumerSDK.AddParam("sort_by", ""+sortBy);
        }

        if (sortOrder != null && !sortOrder.equals("")){
            consumerSDK.AddParam("sort_order", ""+sortOrder);
        }

        // Primeiro Busca registro no cache, se não tiver busca no servidor
        if (methodType == null) {
            consumerSDK.setUseCache(true);
            textJson = CacheManager.getDataJSONArray(consumerSDK);
        }
        // Busca registro só no cache
        else if (methodType.equals(MethodType.CACHE_YES)) {
            textJson = CacheManager.getDataJSONArrayCache(consumerSDK);
        }
        // Verifica se o arquivo já possui cache, se possui um cache recente,
        // não faz nada senão busca no servidor
        // Se não possuir cache, busca no servidor
        else if (methodType.equals(MethodType.CACHE_TIME)) {
            textJson = CacheManager.getDataJSONArrayServer(consumerSDK, false, true);
        }
        // Busca do servidor independente do cache
        else if (methodType.equals(MethodType.CACHE_NO)) {
            textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);
        }

        if (textJson != null) {

            Gson gson = new Gson();

            JsonParser parser = new JsonParser();

            JsonElement element;

            try {
                element = parser.parse(textJson);
            } catch (Exception e) {

                CacheManager.invalidateCache(consumerSDK);
                Log.e("Service_1_3", "getLoggedUserCache JSON Error: " + e.getMessage());
                throw new Exception(Utils.getContextApplication().getString(R.string.errorMessage500));

            }

            listAvaliationReturn = gson.fromJson(element, ListAvaliationReturn.class);

            if (listAvaliationReturn.getAvaliationReturns() != null){
                avaliationReturns.addAll(listAvaliationReturn.getAvaliationReturns());
            }

        }

        return avaliationReturns;

    }

    public ArrayList<AvaliationReturn> searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{

        ArrayList<AvaliationReturn> avaliationReturns = new ArrayList<AvaliationReturn>();

        ListAvaliationReturn listAvaliationReturn = null;

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes/busca";

        RestSKD consumerSDK = new RestSKD(context);

        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);

        consumerSDK.AddParam("q", pattern);

        consumerSDK.AddParam("limit", ""+limit);

        consumerSDK.AddParam("offset", ""+offset);

        if (status != null && !status.equals("")){
            consumerSDK.AddParam("s", ""+status);
        }

        if (sortBy != null && !sortBy.equals("")){
            consumerSDK.AddParam("sort_by", ""+sortBy);
        }

        if (sortOrder != null && !sortOrder.equals("")){
            consumerSDK.AddParam("sort_order", ""+sortOrder);
        }

        // Primeiro Busca registro no cache, se não tiver busca no servidor
        if (methodType == null) {
            consumerSDK.setUseCache(true);
            textJson = CacheManager.getDataJSONArray(consumerSDK);
        }
        // Busca registro só no cache
        else if (methodType.equals(MethodType.CACHE_YES)) {
            textJson = CacheManager.getDataJSONArrayCache(consumerSDK);
        }
        // Verifica se o arquivo já possui cache, se possui um cache recente,
        // não faz nada senão busca no servidor
        // Se não possuir cache, busca no servidor
        else if (methodType.equals(MethodType.CACHE_TIME)) {
            textJson = CacheManager.getDataJSONArrayServer(consumerSDK, false, true);
        }
        // Busca do servidor independente do cache
        else if (methodType.equals(MethodType.CACHE_NO)) {
            textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);
        }

        if (textJson != null) {

            Gson gson = new Gson();

            JsonParser parser = new JsonParser();

            JsonElement element;

            try {
                element = parser.parse(textJson);
            } catch (Exception e) {

                CacheManager.invalidateCache(consumerSDK);
                Log.e("Service_1_3", "getLoggedUserCache JSON Error: " + e.getMessage());
                throw new Exception(Utils.getContextApplication().getString(R.string.errorMessage500));

            }

            listAvaliationReturn = gson.fromJson(element, ListAvaliationReturn.class);

            if (listAvaliationReturn.getAvaliationReturns() != null){
                avaliationReturns.addAll(listAvaliationReturn.getAvaliationReturns());
            }

        }

        return avaliationReturns;

    }

    public String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception{

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes/busca/" + id;

        RestSKD consumerSDK = new RestSKD(context);

        consumerSDK.AddHeader("Authorization", "valor");

        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);

        // Primeiro Busca registro no cache, se não tiver busca no servidor
        if (methodType == null) {
            consumerSDK.setUseCache(true);
            textJson = CacheManager.getDataJSONArray(consumerSDK);
        }
        // Busca registro só no cache
        else if (methodType.equals(MethodType.CACHE_YES)) {
            textJson = CacheManager.getDataJSONArrayCache(consumerSDK);
        }
        // Verifica se o arquivo já possui cache, se possui um cache recente,
        // não faz nada senão busca no servidor
        // Se não possuir cache, busca no servidor
        else if (methodType.equals(MethodType.CACHE_TIME)) {
            textJson = CacheManager.getDataJSONArrayServer(consumerSDK, false, true);
        }
        // Busca do servidor independente do cache
        else if (methodType.equals(MethodType.CACHE_NO)) {
            textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);
        }

        return textJson;
    }


    public UserReturn getUser(Context context) throws Exception {
        String idContrato = "";
        String idUsuario = "";
        String textJson = "";

        UserReturn userReturn = null;
        TokenConvertedReturn tokenConvertedReturn = getTokenConverted(context);
        idContrato = tokenConvertedReturn.getUserContrato();
        idUsuario = tokenConvertedReturn.getUserId();
        String URL = "https://apicarmaix1.websiteseguro.com/v1/contratos/"+idContrato+"/usuarios/"+idUsuario;

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);
        consumerSDK.setCacheTime(999999999l);

        textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);

        if (textJson != null) {

            Gson gson = new Gson();

            JsonParser parser = new JsonParser();

            JsonElement element;

            try {
                element = parser.parse(textJson);
            } catch (Exception e) {

                CacheManager.invalidateCache(consumerSDK);
                Log.e("Service_1_3", "getLoggedUserCache JSON Error: " + e.getMessage());
                throw new Exception(Utils.getContextApplication().getString(R.string.errorMessage500));

            }

            userReturn = gson.fromJson(element, UserReturn.class);

        }

        return userReturn;
    }

    public TokenConvertedReturn getTokenConverted(Context context) throws JSONException {
        ApplicationCarmaix application = (ApplicationCarmaix) context.getApplicationContext();
        String token = application.getLoginTable().getToken();
        TokenConvertedReturn tokenConvertedReturn = null;
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        byte[] jsonByte = base64.decode(token.getBytes());
        String converted = new String(jsonByte);
        JSONObject jsonObject = new JSONObject(converted.substring(converted.lastIndexOf("\"data\":")+"\"data\":".length()));

        String textJson = jsonObject.toString();

        if (textJson != null) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonElement element;
            element = parser.parse(textJson);
            tokenConvertedReturn = gson.fromJson(element, TokenConvertedReturn.class);
        }

        return tokenConvertedReturn;
    }
}
