package br.com.carmaix.services;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import br.com.carmaix.R;
import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.cache.CacheManager;
import br.com.carmaix.spinnerStaticValues.ClassificacaoReturn;
import br.com.carmaix.spinnerStaticValues.MotivoAvaliacaoReturn;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;
import br.com.carmaix.utils.ValueLabelDefault;

public class ServiceDefault implements VersionRelease {
    public final static String BASE_URL = "https://apicarmaix1.websiteseguro.com/v1";

    public TokenReturn login(Context context, String user, String password) throws Exception{

        TokenReturn tokenReturn = null;

        String textJson = "";

        String URL = this.BASE_URL+"/auth/login";

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

        String URL = this.BASE_URL+"/avaliacoes";

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

        String URL = this.BASE_URL+"/avaliacoes/busca";

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

        String URL = this.BASE_URL+"/avaliacoes/busca/" + id;

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
        String URL = this.BASE_URL+"/contratos/"+idContrato+"/usuarios/"+idUsuario;

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

    public ArrayList<ValueLabelDefault> listVendedor(Context context) throws Exception {
        String idContrato = "";
        String textJson = "";

        ArrayList<ValueLabelDefault> vendedorReturn = Utils.createArrayDefault(new VendedorReturn(context));
        TokenConvertedReturn tokenConvertedReturn = getTokenConverted(context);
        idContrato = tokenConvertedReturn.getUserContrato();
        String URL = this.BASE_URL+"/contratos/"+idContrato+"/usuarios?t=vendas";

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            VendedorReturn[] v = gson.fromJson(element, VendedorReturn[].class);
            if(v.length > 0) {
                vendedorReturn.addAll(Arrays.asList(v));
            }

        }

        return vendedorReturn;

    }

    public ArrayList<ValueLabelDefault> listCategorias(Context context) throws Exception {
        String textJson = "";

        ArrayList<ValueLabelDefault> categoriaReturn = Utils.createArrayDefault(new CategoriaReturn(context));
        String URL = this.BASE_URL+"/veiculos/categorias";

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            CategoriaReturn[] v = gson.fromJson(element, CategoriaReturn[].class);
            if(v.length > 0) {
                categoriaReturn.addAll(Arrays.asList(v));
            }

        }

        return categoriaReturn;

    }

    public ArrayList<ValueLabelDefault> listCombustiveis(Context context) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/combustiveis";
        ArrayList<ValueLabelDefault> commbustiveisReturn = Utils.createArrayDefault(new CombustiveisReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            CombustiveisReturn[] v = gson.fromJson(element, CombustiveisReturn[].class);
            if(v.length > 0) {
                commbustiveisReturn.addAll(Arrays.asList(v));
            }

        }

        return commbustiveisReturn;

    }

    public ArrayList<ValueLabelDefault> listMarcasCategoria(Context context, String id) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/categorias/"+id+"/marcas";
        ArrayList<ValueLabelDefault> marcasCategoriaReturn = Utils.createArrayDefault(new MarcasCategoriaReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            MarcasCategoriaReturn[] v = gson.fromJson(element, MarcasCategoriaReturn[].class);
            if(v.length > 0) {
                marcasCategoriaReturn.addAll(Arrays.asList(v));
            }

        }

        return marcasCategoriaReturn;

    }


    public ArrayList<ValueLabelDefault> listModelosMarca(Context context, String id) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/marcas/"+id+"/modelos";
        ArrayList<ValueLabelDefault> modelosMarcaReturn = Utils.createArrayDefault(new ModelosMarcaReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            ModelosMarcaReturn[] v = gson.fromJson(element, ModelosMarcaReturn[].class);
            if(v.length > 0) {
                modelosMarcaReturn.addAll(Arrays.asList(v));
            }

        }

        return modelosMarcaReturn;

    }

    public ArrayList<ValueLabelDefault> listAnoFabricacao(Context context, String id) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/modelos/"+id+"/anos";
        ArrayList<ValueLabelDefault> anoFabricacaoReturn = Utils.createArrayDefault(new AnoFabricacaoReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            AnoFabricacaoReturn[] v = gson.fromJson(element, AnoFabricacaoReturn[].class);
            if(v.length > 0) {
                anoFabricacaoReturn.addAll(Arrays.asList(v));
            }

        }

        return anoFabricacaoReturn;

    }


    public ArrayList<ValueLabelDefault> listAnoModelo(Context context, String id, String ano) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/modelos/"+id+"/anos/"+ano;
        ArrayList<ValueLabelDefault> anoModeloReturn = Utils.createArrayDefault(new AnoModeloReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            AnoModeloReturn[] v = gson.fromJson(element, AnoModeloReturn[].class);
            if(v.length > 0) {
                anoModeloReturn.addAll(Arrays.asList(v));
            }

        }

        return anoModeloReturn;

    }

    public ArrayList<ValueLabelDefault> listOpcionais(Context context) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/opcionais";
        ArrayList<ValueLabelDefault> opcionaisReturn = new ArrayList<>();

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            OpcionaisReturn[] v = gson.fromJson(element, OpcionaisReturn[].class);
            if(v.length > 0) {
                opcionaisReturn.addAll(Arrays.asList(v));
            }

        }

        return opcionaisReturn;

    }


    public ArrayList<ValueLabelDefault> listCidades(Context context, String uf) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/localizacao/estados/"+uf+"/cidades";
        ArrayList<ValueLabelDefault> cidadesReturn = Utils.createArrayDefault(new CidadesReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            CidadesReturn[] v = gson.fromJson(element, CidadesReturn[].class);
            if(v.length > 0) {
                cidadesReturn.addAll(Arrays.asList(v));
            }

        }

        return cidadesReturn;

    }

    public ArrayList<ValueLabelDefault> listCombustiveisModelos(Context context, String modelo) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/modelos/"+modelo+"/combustiveis";
        ArrayList<ValueLabelDefault> commbustiveisModelosReturn = Utils.createArrayDefault(new CombustiveisModelosReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            CombustiveisModelosReturn[] v = gson.fromJson(element, CombustiveisModelosReturn[].class);
            if(v.length > 0) {
                commbustiveisModelosReturn.addAll(Arrays.asList(v));
            }

        }

        return commbustiveisModelosReturn;

    }

    public ArrayList<ValueLabelDefault> listMotivoAvaliacao(Context context) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/avaliacoes/motivos";
        ArrayList<ValueLabelDefault> motivoAvaliacaoReturn = Utils.createArrayDefault(new MotivoAvaliacaoReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            MotivoAvaliacaoReturn[] v = gson.fromJson(element, MotivoAvaliacaoReturn[].class);
            if(v.length > 0) {
                motivoAvaliacaoReturn.addAll(Arrays.asList(v));
            }

        }

        return motivoAvaliacaoReturn;

    }

    public ArrayList<ValueLabelDefault> listClassificacoes(Context context) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/classificacoes";
        ArrayList<ValueLabelDefault> classificacaoReturn = Utils.createArrayDefault(new ClassificacaoReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            ClassificacaoReturn[] v = gson.fromJson(element, ClassificacaoReturn[].class);
            if(v.length > 0) {
                classificacaoReturn.addAll(Arrays.asList(v));
            }

        }

        return classificacaoReturn;

    }

    public ArrayList<ValueLabelDefault> listAnoCombustivel(Context context, String modelo, String combustivel) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/modelos/"+modelo+"/combustiveis/"+combustivel+"/anos";
        ArrayList<ValueLabelDefault> anosCombustivelReturn = Utils.createArrayDefault(new AnosCombustivelReturn(context));

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            AnosCombustivelReturn[] v = gson.fromJson(element, AnosCombustivelReturn[].class);
            if(v.length > 0) {
                anosCombustivelReturn.addAll(Arrays.asList(v));
            }

        }

        return anosCombustivelReturn;

    }


    public ValorMedioReturn getValorMedio(Context context, String modelo,String ano, String combustivel) throws Exception {
        String textJson = "";

        String URL = this.BASE_URL+"/veiculos/modelos/"+modelo+"/anos/"+ano+"/combustiveis/"+combustivel+"/passecarros";
        ValorMedioReturn valorMedioReturn = null;

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

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

            valorMedioReturn = gson.fromJson(element, ValorMedioReturn.class);
        }

        return valorMedioReturn;
    }

    public EstatisticaReturn getEstatistica(Context context, String modelo, String ano, String combustivel) throws Exception {
        String textJson = "";
        JsonElement element = null;
        EstatisticaReturn estatisticaReturn = null;

        String anoSplit = ano.split("-")[0];

        String URL = this.BASE_URL+"/veiculos/modelos/"+modelo+"/anos/"+anoSplit+"/combustiveis/"+combustivel+"/estatisticas";

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

        textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);
        if (textJson != null) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();


            try {
                element = parser.parse(textJson);
            } catch (Exception e) {
                CacheManager.invalidateCache(consumerSDK);
                Log.e("Service_1_3", "getLoggedUserCache JSON Error: " + e.getMessage());
                throw new Exception(Utils.getContextApplication().getString(R.string.errorMessage500));
            }

            estatisticaReturn = gson.fromJson(element, EstatisticaReturn.class);

        }

        return estatisticaReturn;
    }


    public InformacoesAvaliacaoReturn listInformacaoAvaliacao(Context context, String idAvaliacao) throws Exception {
        String textJson = "";
        JsonElement element = null;

        InformacoesAvaliacaoReturn informacoesAvaliacaoReturn = null;


        String URL = this.BASE_URL+"/avaliacoes/"+idAvaliacao;

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setUrlFull(URL);

        textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);
        if (textJson != null) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();


            try {
                element = parser.parse(textJson);
            } catch (Exception e) {
                CacheManager.invalidateCache(consumerSDK);
                Log.e("Service_1_3", "getLoggedUserCache JSON Error: " + e.getMessage());
                throw new Exception(Utils.getContextApplication().getString(R.string.errorMessage500));
            }

            informacoesAvaliacaoReturn = gson.fromJson(element, InformacoesAvaliacaoReturn.class);

        }
        return informacoesAvaliacaoReturn;
    }

    public UserReturn getAvaliador(Context context, String idUsuario) throws Exception {
        String idContrato = "";
        String textJson = "";

        UserReturn userReturn = null;
        TokenConvertedReturn tokenConvertedReturn = getTokenConverted(context);
        idContrato = tokenConvertedReturn.getUserContrato();
        String URL = this.BASE_URL+"/contratos/"+idContrato+"/usuarios/"+idUsuario;

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


    public String sendEsqueciSenha(Context context, String email) throws Exception {
        String textJson = "";
        String message = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email",email);

        jsonObject.toString();
        String URL = this.BASE_URL+"/auth/forgot-password";

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.PUT);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);
        consumerSDK.setCacheTime(999999999l);
        consumerSDK.setContentType("application/json");
        consumerSDK.AddBinaryBodyParam(jsonObject.toString().getBytes());
        textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);

        JSONObject jsonObjectReturn = new JSONObject(textJson);
        message = jsonObjectReturn.get("description").toString();

        return message;
    }

    public HashMap<String, Object> alterarSenha(Context context, String senhaAtual, String novaSenha, String confirmacaoSenha) throws Exception {
        String textJson = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password",senhaAtual);
        jsonObject.put("new_password",novaSenha);
        jsonObject.put("new_confirm",confirmacaoSenha);

        jsonObject.toString();
        String URL = this.BASE_URL+"/auth/change-password";

        RestSKD consumerSDK = new RestSKD(context);
        consumerSDK.setMethodHttpType(MethodHttpType.PUT);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(URL);
        consumerSDK.setCacheTime(999999999l);
        consumerSDK.setContentType("application/json");
        consumerSDK.AddBinaryBodyParam(jsonObject.toString().getBytes());
        textJson = CacheManager.getDataJSONArrayServer(consumerSDK, true);

        JSONObject jsonObjectReturn = new JSONObject(textJson);
        String code = (String)jsonObjectReturn.get("code");

        ArrayList<String> messageList = new ArrayList<String>();
        HashMap<String, Object> hashMapReturn = new HashMap<>();

        if(!code.equals("200")) {
            JSONArray jsonArray = (JSONArray)jsonObjectReturn.get("errors");

            for(int i = 0; i < jsonArray.length(); i++){
                messageList.add(jsonArray.getJSONObject(i).getString("message"));
            }

        }else {
            messageList.add(jsonObjectReturn.get("description").toString());
        }

        hashMapReturn.put("code",code);
        hashMapReturn.put("message",messageList);

        return hashMapReturn;
    }
}

