package br.com.carmaix.services;


import br.com.carmaix.cache.CacheManager;
import br.com.carmaix.utils.Constants;

public class ServiceDefault implements VersionRelease {

	public boolean isSupportWidgetIdentity() throws Exception{

        // Exemplo de consumo de rest

        String textJson = "";

        String URL = "/socialsimpleapi/api/rest/social/listGroupedNotifications";

        RestSKD consumerSDK = new RestSKD(null);
        consumerSDK.setMethodHttpType(MethodHttpType.GET);
        consumerSDK.setCacheTime(Constants.CACHE_TIME);
        consumerSDK.setUrlFull(consumerSDK.getHost() + URL);
        consumerSDK.setCacheTime(999999999l);

        MethodType methodType = MethodType.CACHE_TIME;

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

        consumerSDK.setReturnJson(textJson);


        return false;
    }

}
