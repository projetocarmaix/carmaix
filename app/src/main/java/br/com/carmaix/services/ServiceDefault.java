package br.com.carmaix.services;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import br.com.carmaix.R;
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
        consumerSDK.setCacheTime(999999999l);

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

    public String listAvaliations(Context context, MethodType methodType, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes";

        RestSKD consumerSDK = new RestSKD(context);

        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);
        consumerSDK.setCacheTime(999999999l);

        /*
        consumerSDK.AddParam("limit", ""+limit);

        consumerSDK.AddParam("offset", ""+offset);

        consumerSDK.AddParam("s", ""+status);

        consumerSDK.AddParam("sort_by", ""+sortBy);

        consumerSDK.AddParam("sort_order", ""+sortOrder);
        */

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

    public String searchAvaliations(Context context, MethodType methodType, String pattern, int limit, int offset, String status, String sortBy, String sortOrder) throws Exception{

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes/busca/" + pattern;

        RestSKD consumerSDK = new RestSKD(context);

        consumerSDK.AddHeader("Authorization", "valor");

        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);
        consumerSDK.setCacheTime(999999999l);

        consumerSDK.AddParam("limit", ""+limit);

        consumerSDK.AddParam("offset", ""+offset);

        consumerSDK.AddParam("s", ""+status);

        consumerSDK.AddParam("sort_by", ""+sortBy);

        consumerSDK.AddParam("sort_order", ""+sortOrder);

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

    public String getAvaliationsDetail(Context context, MethodType methodType, int id) throws Exception{

        String textJson = "";

        String URL = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes/busca/" + id;

        RestSKD consumerSDK = new RestSKD(context);

        consumerSDK.AddHeader("Authorization", "valor");

        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);
        consumerSDK.setCacheTime(999999999l);

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

}
