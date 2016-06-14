package br.com.carmaix.services;

import android.content.Context;

import android.net.http.AndroidHttpClient;
import android.util.Log;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import java.net.URI;
import java.util.ArrayList;

import java.net.URI;
import java.util.ArrayList;

import br.com.carmaix.application.ApplicationCarmaix;
import br.com.carmaix.utils.Constants;
import br.com.carmaix.utils.Utils;

public class RestSKD {

    boolean ignoreNewResult = false;

    private String customControlParam = "";

    boolean useGZipEncoding = false;

    private boolean verifyFileDate = false;

    private String nameUrlCustom = "";

    private String nameCustomArchive = "";

    private String pathCustomSaveCache = "";

    private MethodType methodType;

    protected String returnJson = "";

    protected String defaultLanguage = "pt-BR";

    protected String host;

    protected String documentExtension = ".txt";

    protected String imageExtension = ".png";

    protected int TIMEOUT = 30000;

    protected String urlFull = "";

    protected String contentType = "";

    protected long cacheTime = 0;

    protected boolean useCache = false;

    protected boolean isCritical = true;

    protected ArrayList<NameValuePair> params;

    protected final ArrayList<NameValuePair> headers;

    protected final ArrayList<NameValuePair> bodyParams;

    protected byte[] binaryBodyParam;

    protected Context context;

    protected Boolean authenticateService = Boolean.TRUE;

    protected MethodHttpType methodHttpType = MethodHttpType.GET;

    // Variável responsável por detreminar se os tokens usados serão da rede
    // logada selecionada ou de um servidor armazenado no banco de dados
    private Boolean useAuthenticationDefault = Boolean.TRUE;
    // Variáveis usadas para usar o token escolhido no lugar do token que está
    // logado
    private String AuthenticationAccesToken = "";
    private String AuthenticationPrivateToken = "";

    // hoje existem 2 projetos o social e o ECM. o social retorna códigos
    // numéricos e o ECM retorna a mensagem prnta
    // esta variável controla como retornar o erro até que seja padronizado
    private Boolean catchErrorECM = false;

    private String pathRest = "/restCache/";

    private String pathRoot = "/Android/data/" + Constants.APP + "/";

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean isCritical) {
        this.isCritical = isCritical;
    }

    public String getReturnJson() {
        return returnJson;
    }

    public void setReturnJson(String returnJson) {
        this.returnJson = returnJson;
    }

    public String getPathCustomSaveCache() {
        return pathCustomSaveCache;
    }

    public String getNameUrlCustom() {
        return nameUrlCustom;
    }

    public void setNameUrlCustom(String nameUrlCustom) {
        this.nameUrlCustom = nameUrlCustom;
    }

    public void setPathCustomSaveCache(String pathCustomSaveCache) {
        this.pathCustomSaveCache = pathCustomSaveCache;
    }

    public String getNameCustomArchive() {
        return nameCustomArchive;
    }

    public void setNameCustomArchive(String nameCustomArchive) {
        this.nameCustomArchive = nameCustomArchive;
    }

    public boolean isUseGZipEncoding() {
        return useGZipEncoding;
    }

    public void setUseGZipEncoding(boolean useGZipEncoding) {
        this.useGZipEncoding = useGZipEncoding;
    }

    public RestSKD(Context contextApplication) {

        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
        bodyParams = new ArrayList<NameValuePair>();
        context = contextApplication;

        try {

            setHost("");

            AddHeader("Accept-Language", getDefaultLanguage());

            AddHeader("User-Agent", System.getProperty("http.agent"));

            ApplicationCarmaix application = (ApplicationCarmaix) context.getApplicationContext();

            if (application.getLoginTable() != null){
                AddHeader("Authorization", application.getLoginTable().getToken());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public MethodHttpType getMethodHttpType() {
        return methodHttpType;
    }

    public void setMethodHttpType(MethodHttpType methodHttpType) {
        this.methodHttpType = methodHttpType;
    }

    public String getUrlFull() {
        return urlFull;
    }

    public void setUrlFull(String urlFull) {
        this.urlFull = urlFull;
    }

    public void AddParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public void AddBodyParam(String name, String value) {
        bodyParams.add(new BasicNameValuePair(name, value));
    }

    public void AddBinaryBodyParam(byte[] binaryParam) {
        binaryBodyParam = binaryParam;
    }

    public void RemoveParams() {
        params.clear();
        bodyParams.clear();
    }

    public void AddHeader(String name, String value) {
        headers.add(new BasicNameValuePair(name, value));
    }

    public ArrayList<NameValuePair> getParams() {
        return params;
    }

    public void setParams(ArrayList<NameValuePair> params) {
        this.params = params;
    }

    public String getDocumentExtension() {
        return documentExtension;
    }

    public void setDocumentExtension(String documentExtension) {
        this.documentExtension = documentExtension;
    }

    public Boolean getAuthenticateService() {
        return authenticateService;
    }

    public void setAuthenticateService(Boolean authenticateService) {
        this.authenticateService = authenticateService;
    }

    public Boolean getUseAuthenticationDefault() {
        return useAuthenticationDefault;
    }

    public void setUseAuthenticationDefault(Boolean useAuthenticationDefault) {
        this.useAuthenticationDefault = useAuthenticationDefault;
    }

    public String getAuthenticationAccesToken() {
        return AuthenticationAccesToken;
    }

    public void setAuthenticationAccesToken(String authenticationAccesToken) {
        AuthenticationAccesToken = authenticationAccesToken;
    }

    public String getAuthenticationPrivateToken() {
        return AuthenticationPrivateToken;
    }

    public void setAuthenticationPrivateToken(String authenticationPrivateToken) {
        AuthenticationPrivateToken = authenticationPrivateToken;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public Boolean getCatchErrorECM() {
        return catchErrorECM;
    }

    public void setCatchErrorECM(Boolean catchErrorECM) {
        this.catchErrorECM = catchErrorECM;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public boolean isVerifyFileDate() {
        return verifyFileDate;
    }

    public void setVerifyFileDate(boolean verifyFileDate) {
        this.verifyFileDate = verifyFileDate;
    }

    public String getCustomControlParam() {
        return customControlParam;
    }

    public void setCustomControlParam(String customControlParam) {
        this.customControlParam = customControlParam;
    }

    public String execute() throws Exception {
        return this.execute("application/json; charset=\"UTF-8\"");
    }

    public boolean isIgnoreNewResult() {
        return ignoreNewResult;
    }

    public void setIgnoreNewResult(boolean ignoreNewResult) {
        this.ignoreNewResult = ignoreNewResult;
    }

    public String getPathRest() {
        return pathRest;
    }

    public void setPathRest(String pathRest) {
        this.pathRest = pathRest;
    }

    public String getPathRoot() {
        return pathRoot;
    }

    public void setPathRoot(String pathRoot) {
        this.pathRoot = pathRoot;
    }

    public String execute(String contentType) throws Exception {

        String url = urlFull;

        String result = "";

        switch (methodHttpType) {

            case GET: {

                URI url2 = new URI(getUrlRequisition(url));

                if (Constants.DEBUG_TEMPO_RESPOSTA_SERVICE) {
                    Log.i("DEBUG Rest URL", "xxx " + url2);
                }

                HttpGet get = new HttpGet(url2);

                addToGetHeader(contentType, get);

                if (isUseGZipEncoding()){
                    AndroidHttpClient.modifyRequestToAcceptGzipResponse(get);
                }

                get.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                result = executeRequest(get);

                break;
            } case POST: {

                HttpPost post = new HttpPost(url);

                addToPostHeader(contentType, post);

                post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

                result = executeRequest(post);
                break;
            }
            case GET_AND_POST: {

                URI url2 = new URI(getUrlRequisition(url));

                HttpPost post = new HttpPost(url2);

                addToPostHeader(contentType, post);

                post.setEntity(new UrlEncodedFormEntity(bodyParams, HTTP.UTF_8));
                post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                result = executeRequest(post);
                break;
            }
            case GET_AND_BinaryPOST: {

                URI url2 = new URI(getUrlRequisition(url));

                HttpPost post = new HttpPost(url2);

                addToPostHeader(contentType, post);

                post.setEntity(new ByteArrayEntity(binaryBodyParam));
                post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                result = executeRequest(post);
                break;
            }
            case GET_AND_POST_WITHOUT_USE_EXPECT_CONTINUE_PARAM: {

                URI url2 = new URI(getUrlRequisition(url));

                HttpPost post = new HttpPost(url2);

                addToPostHeader(contentType, post);

                post.setEntity(new UrlEncodedFormEntity(bodyParams, HTTP.UTF_8));
                // post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE,
                // false);
                result = executeRequest(post);
                break;
            }
            case POST_:

                URI url2 = new URI(getUrlRequisition(url));

                HttpPost post = new HttpPost(url2);

                addToPostHeader(contentType, post);

                if (binaryBodyParam != null) {
                    post.setEntity(new ByteArrayEntity(binaryBodyParam));
                }

                post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                result = executeRequest(post);
                break;

        }

        return result;

    }

    protected String executeRequest(HttpGet request) throws Exception {

        String result = "";

        try {

            if (Utils.isConnected(context)) {

                long start = System.currentTimeMillis();

                HttpClient client = new DefaultHttpClient();

                HttpParams httpParameters = client.getParams();
                HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);

                HttpResponse response = client.execute(request);

                result = Utils.verifyResponseCode(context, response, getCatchErrorECM(), true, isUseGZipEncoding(), isIgnoreNewResult());

                long end = System.currentTimeMillis();

                if (Constants.DEBUG_TEMPO_RESPOSTA_SERVICE) {
                    Log.i("DEBUG Rest Tempo", "xxx " + request.getURI().toString() + " :" + (end - start));
                }

                return result;

            } else {
                throw new Exception("Dispositivo não está conectado");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } catch (Throwable e) {
            throw new Exception(e.getMessage());
        }
    }

    private String executeRequest(HttpPost request) throws Exception {

        String result = "";

        try {

            if (Utils.isConnected(context)) {

                long start = System.currentTimeMillis();

                HttpClient client = new DefaultHttpClient();

                HttpParams httpParameters = client.getParams();
                HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT);

                HttpResponse response = client.execute(request);

                result = Utils.verifyResponseCode(context, response, getCatchErrorECM(), true, isUseGZipEncoding(), isIgnoreNewResult());

                long end = System.currentTimeMillis();

                if (Constants.DEBUG_TEMPO_RESPOSTA_SERVICE) {
                    Log.i("DEBUG Rest Tempo", "xxx " + request.getURI().toString() + " :" + (end - start));
                }

            } else {
                throw new Exception("Dispositivo não está conectado");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } catch (Throwable e) {
            throw new Exception(e.getMessage());
        }

        return result;

    }

    private String getUrlRequisition(String url) throws Exception {
        return url
                + (("?" + URLEncodedUtils.format(params, HTTP.UTF_8)).trim().length() <= 1 ? "" : "?"
                        + URLEncodedUtils.format(params, HTTP.UTF_8));
    }

    private void addToGetHeader(String contentType, HttpGet httpGet){

        if (headers.size() > 0) {

            httpGet.addHeader("Content-Type", contentType);

            for (NameValuePair nameValuePair : headers) {
                httpGet.setHeader(nameValuePair.getName(), nameValuePair.getValue());
            }

        } else {
            httpGet.setHeader("Content-Type", contentType);
        }

    }

    private void addToPostHeader(String contentType, HttpPost httpPost){

        if (headers.size() > 0) {

            httpPost.addHeader("Content-Type", contentType);

            for (NameValuePair nameValuePair : headers) {
                httpPost.setHeader(nameValuePair.getName(), nameValuePair.getValue());
            }

        } else {
            httpPost.setHeader("Content-Type", contentType);
        }

    }

}
