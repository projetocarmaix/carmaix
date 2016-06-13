package br.com.carmaix.cache;

import android.util.Log;

import org.apache.http.client.utils.URLEncodedUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import br.com.carmaix.crypto.digests.SHA1Digest;
import br.com.carmaix.exceptions.CacheException;
import br.com.carmaix.services.RestSKD;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;

public class CacheManager {

    public static String getDataJSONArray(RestSKD consumer) throws Exception {

        String data = getStringData(consumer);

        if (data != null) {
            return data;
        }
        return null;
    }

    public static String getStringData(RestSKD consumer) throws Exception {
        String data = null;

        if (consumer.isUseCache()) {

            // Caso ocorra algum erro de leitura no arquivo, os dados são
            // novamente buscados no servidor
            // Tratamento feito para mesmo que acontece erro de leitura e
            // gravação, o usuário possa vizualizar os dados

            try {
                data = getStringCache(consumer);
            } catch (CacheException e) {

                if (Constants.DEBUG_ACTIONS_CACHE) {
                    Log.i("DEBUG CACHE", " xxx Error get cache ### " + e.getMessage());
                }

                data = null;
            }

            if (data == null || data.equals("") || data.equals("[]") || (data.length() < 5 && data.contains("[]"))) {

                if (Constants.DEBUG_ACTIONS_CACHE) {
                    Log.i("DEBUG CACHE", " xxx get server ### " + consumer.getUrlFull());
                }

                data = getServerData(consumer);
            } else {

                if (Constants.DEBUG_ACTIONS_CACHE) {
                    Log.i("DEBUG CACHE", " xxx get local cache ### " + consumer.getUrlFull());
                }

            }
            return data;
        } else {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx get server ### " + consumer.getUrlFull());
            }

            data = getServerData(consumer);
        }

        return data;
    }

    private static String getServerData(RestSKD consumer) throws Exception {

        String data = null;

        if (consumer.getContentType().equals("")) {
            data = consumer.execute();
        } else {
            data = consumer.execute(consumer.getContentType());
        }

        if (consumer.getCacheTime() > 0) {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx Salva cache ### " + consumer.getUrlFull());
            }

            try {
                saveStringCache(consumer, data);
            } catch (CacheException ex) {
                if (Constants.DEBUG_ACTIONS_CACHE) {
                    Log.i("DEBUG CACHE", " xxx Error Save cache ### " + ex.getMessage());
                }
            }

        }

        return data;
    }

    public static String getUrlRequisition(RestSKD consumer) throws CacheException {
        return (consumer.getNameUrlCustom() != null && !consumer.getNameUrlCustom().equals("") ? consumer.getNameUrlCustom() : consumer.getUrlFull()
                + (("?" + URLEncodedUtils.format(consumer.getParams(), "UTF-8")).trim().length() <= 1 ? "" : "?"
                + URLEncodedUtils.format(consumer.getParams(), "UTF-8")));
    }

    private static String getStringCache(RestSKD consumer) throws CacheException {

        String file = getUrlRequisition(consumer);

        return readTextFile(
                getDirToCache(consumer) + getPathToSave(consumer, consumer.getPathRest()) + getNameFileToCache(file) + consumer.getDocumentExtension(),
                minutesToMilliseconds(consumer.getCacheTime()));
    }

    public static String getDirToCache(RestSKD consumer) throws CacheException {
        return getDirToCache(false, consumer);
    }

    public static String getDirToCache(boolean pathBaseOnly, RestSKD consumer) throws CacheException {

        if (Utils.isExternalStorageWritable()) {

            File root;

            root = Utils.getContextApplication().getExternalFilesDir(null);

            File cacheDir = new File(root, consumer.getPathRoot());

            if (!cacheDir.exists()) {
                cacheDir.mkdirs();

                // Cria o arquivo .nomedia
                File arqNoGallery = new File(cacheDir, ".nomedia");

                if (!arqNoGallery.exists()) {

                    try {
                        arqNoGallery.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (!pathBaseOnly) {

                // Inclusão de diretório para armazenar o servidor
                String baseUrl = "global_cache";

                File cacheDirServer = new File(cacheDir, baseUrl);

                if (!cacheDirServer.exists()) {
                    cacheDirServer.mkdirs();
                }

                return cacheDirServer.getAbsolutePath();

            }

            return cacheDir.getAbsolutePath();

        } else {

            throw new CacheException("Error Storage");

        }

    }

    public static String getNameFileToCache(String url) {

        SHA1Digest temp = new SHA1Digest();
        String hash = "";

        try {
            temp.update(url.getBytes("UTF-8"), 0, url.getBytes("UTF-8").length);
            byte[] mac = new byte[temp.getDigestSize()];
            temp.doFinal(mac, 0);

            for (int i = 0; i < mac.length; i++) {
                hash += Integer.toString((mac[i] & 0xff) + 0x100, 16).substring(1);
            }

        } catch (UnsupportedEncodingException e1) {
            // Log.error(e1.getMessage());
        }
        temp = null;
        return hash;
    }

    private static void saveStringCache(RestSKD consumer, String data) throws CacheException {

        String file = getUrlRequisition(consumer);

        writeTextFile(getDirToCache(consumer) + getPathToSave(consumer, consumer.getPathRest()),
                getNameFileToCache(file) + consumer.getDocumentExtension(), data);
    }

    private static String readTextFile(String fName, long fileAge) throws CacheException {

        String result = null;

        FileInputStream input = null;
        byte[] buffer = null;
        File file = null;
        try {

            file = new File(fName);

            if (file.exists()) {

                if ((new Date().getTime() - file.lastModified()) < fileAge) {
                    input = new FileInputStream(fName);
                    buffer = new byte[(int) file.length()];
                    input.read(buffer);
                    result = new String(buffer);
                    input.close();
                } else {}

            }

        } catch (Exception e) {

            try {
                input.close();
            } catch (Exception ex) {}

            throw new CacheException(e.getMessage());
        } finally {

            input = null;
            buffer = null;
            file = null;
        }

        return result;

    }

    private static void writeTextFile(String path, String fName, String text) {

        File cacheDir = null;
        File file = null;
        FileOutputStream out = null;

        try {

            cacheDir = new File(path);

            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }

            file = new File(cacheDir, fName);

            if (file.exists()) {
                file.delete();
                file = new File(cacheDir, fName);
            }

            out = new FileOutputStream(file, true);
            out.write(text.getBytes());
            // out.write("\n".getBytes());
            out.flush();
            out.close();

        } catch (Exception e) {

            e.printStackTrace();

            try {
                out.close();
            } catch (Exception ex) {}

        } finally {

            cacheDir = null;
            file = null;
            out = null;

        }
    }

    private static long minutesToMilliseconds(long minute) {
        return (minute * 60 * 1000);
    }

    public static String getDataJSONArrayCache(RestSKD consumer) throws Exception {
        String data = getStringDataCache(consumer);

        if (data != null) {
            return data;
        }
        return null;
    }

    public static String getStringDataCache(RestSKD consumer) throws Exception {
        String data = null;

        // if (consumer.isUseCache()) {

        // Caso ocorra algum erro de leitura no arquivo, os dados são
        // novamente buscados no servidor
        // Tratamento feito para mesmo que acontece erro de leitura e
        // gravação, o usuário possa vizualizar os dados

        try {
            data = getStringCaches(consumer, true, true);
        } catch (CacheException e) {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx Error get cache ### " + e.getMessage());
            }

            data = null;
        }

        if (data == null || data.equals("") || data.equals("[]") || (data.length() < 5 && data.contains("[]"))) {
            data = null;
        } else {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx get local cache ### " + consumer.getUrlFull());
            }

        }
        return data;
        // } else {
        // Log.i("CACHE", "NAO UTILIZOU CACHE");
        // data = null;
        // }

        // return data;
    }

    private static String getStringCaches(RestSKD consumer, boolean cache, boolean ignoreTime)
            throws CacheException {

        String file = getUrlRequisition(consumer);

        if (cache == true) {
            return readTextFileCache(
                            getDirToCache(consumer) + getPathToSave(consumer,
                            consumer.getPathRest()) + getNameFileToCache(file) + consumer.getDocumentExtension(),
                            minutesToMilliseconds(consumer.getCacheTime()));
        } else {
            return readTextFileServer(
                    getDirToCache(consumer) + getPathToSave(consumer, consumer.getPathRest()) + getNameFileToCache(file) + consumer.getDocumentExtension(),
                    minutesToMilliseconds(consumer.getCacheTime()), ignoreTime);
        }

    }

    private static String readTextFileCache(String fName, long fileAge) throws CacheException {

        String result = null;

        FileInputStream input = null;
        byte[] buffer = null;
        File file = null;
        try {

            file = new File(fName);

            if (file.exists()) {

                input = new FileInputStream(fName);
                buffer = new byte[(int) file.length()];
                input.read(buffer);
                result = new String(buffer);
                input.close();

            }

        } catch (Exception e) {

            try {
                input.close();
            } catch (Exception ex) {}

            throw new CacheException(e.getMessage());
        } finally {

            input = null;
            buffer = null;
            file = null;
        }

        return result;

    }

    public static String getDataJSONArrayServer(RestSKD consumer, boolean ignoreTime) throws Exception {

         String data = getStringDataServer(consumer, ignoreTime);

        if (data != null) {
            return data;
        }
        return null;
    }

    // Novo Método responsável por retornar valores somente quando os resultados
    // são baixados do servidor
    public static String getDataJSONArrayServer(RestSKD consumer, boolean ignoreTime, boolean ignoreResultFromCache)
            throws Exception {

        String data = getStringDataServer(consumer, ignoreTime, ignoreResultFromCache);

        if (data != null) {
            return data;
        }
        return null;
    }

    public static String getStringDataServer(RestSKD consumer, boolean ignoreTime) throws Exception {
        String data = null;

        // if (consumer.isUseCache()) {

        // Caso ocorra algum erro de leitura no arquivo, os dados são
        // novamente buscados no servidor
        // Tratamento feito para mesmo que acontece erro de leitura e
        // gravação, o usuário possa vizualizar os dados

        try {
            data = getStringCaches(consumer, false, ignoreTime);
        } catch (CacheException e) {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx Error get cache ### " + e.getMessage());
            }

            data = null;
        }

        if (data == null || data.equals("") || data.equals("[]") || (data.length() < 5 && data.contains("[]"))) {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx get server ### " + consumer.getUrlFull());
            }

            data = getServerDataCache(consumer);

        } else {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx get local cache ### " + consumer.getUrlFull());
            }

        }
        return data;
        // } else {
        // Log.i("CACHE", "NAO UTILIZOU CACHE");
        // data = getServerData(consumer);
        // }

        // return data;
    }

    // Novo Método que ignora os resultados do cache, retornando apenas os
    // resultados do servidor (Usado nas listas com CACHE_TIME)
    public static String getStringDataServer(RestSKD consumer, boolean ignoreTime, boolean ignoreResultFromCache)
            throws Exception {
        String data = null;

        // if (consumer.isUseCache()) {

        // Caso ocorra algum erro de leitura no arquivo, os dados são
        // novamente buscados no servidor
        // Tratamento feito para mesmo que acontece erro de leitura e
        // gravação, o usuário possa vizualizar os dados

        try {
            data = getStringCaches(consumer, false, ignoreTime);

        } catch (CacheException e) {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx Error get cache ### " + e.getMessage());
            }

            data = null;

        }

        if (data == null || data.equals("") || data.equals("[]") || (data.length() < 5 && data.contains("[]"))) {

            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx get server ### " + consumer.getUrlFull());
            }

            data = getServerDataCache(consumer);

        } else {

            if (ignoreResultFromCache) {
                data = "CACHE";
            } else {
                // data = null;
                Log.i("CACHE", "UTILIZOU CACHE");
            }

        }
        return data;
        // } else {
        // Log.i("CACHE", "NAO UTILIZOU CACHE");
        // data = getServerData(consumer);
        // }

        // return data;
    }

    private static String readTextFileServer(String fName, long fileAge, boolean ignoreTime) throws CacheException {

        String result = null;

        FileInputStream input = null;
        byte[] buffer = null;
        File file = null;
        try {

            file = new File(fName);

            if (file.exists()) {

                if (!ignoreTime) {

                    if ((new Date().getTime() - file.lastModified()) < fileAge) {
                        // return "OK";
                        input = new FileInputStream(fName);
                        buffer = new byte[(int) file.length()];
                        input.read(buffer);
                        result = new String(buffer);
                        input.close();
                    } else {}

                }

            }

        } catch (Exception e) {
            throw new CacheException(e.getMessage());
        } finally {
            file = null;
        }

        return result;

    }

    private static String getServerDataCache(RestSKD consumer) throws Exception {

        String data = null;

        if (consumer.getContentType().equals("")) {
            data = consumer.execute();
        } else {
            data = consumer.execute(consumer.getContentType());
        }

        // if (consumer.getCacheTime() > 0) {

        if (Constants.DEBUG_ACTIONS_CACHE) {
            Log.i("DEBUG CACHE", " xxx Salva cache ### " + consumer.getUrlFull());
        }

        try {

            if (data != null) {
                saveStringCache(consumer, data);
            }

        } catch (CacheException ex) {
            if (Constants.DEBUG_ACTIONS_CACHE) {
                Log.i("DEBUG CACHE", " xxx Error Save cache ### " + ex.getMessage());
            }
        }

        // }

        return data;
    }

    private static String getPathToSave(RestSKD consumerSDK, String pathBase) throws CacheException{
        return (!consumerSDK.getPathCustomSaveCache().equals("") ? consumerSDK.getPathCustomSaveCache() : pathBase);
    }

    public static void invalidateCache(RestSKD consumer) throws CacheException {

        String fileUrl = getUrlRequisition(consumer);

        String tempUrl = getDirToCache(consumer) + getPathToSave(consumer, consumer.getPathRest()) + getNameFileToCache(fileUrl)
                + consumer.getDocumentExtension();

        File file = null;

        try {

            file = new File(tempUrl);

            if (file.exists()) {
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
        }
    }

}
