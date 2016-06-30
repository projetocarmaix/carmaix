package br.com.carmaix.utils;

/**
 * Created by rafael.savaris on 04/05/2016.
 */
public class Constants {

    public static final String APP = "carmaix";

    public static final String APP_BASE = "br.com." + APP;

    public static final boolean DEBUG_TEMPO_RESPOSTA_SERVICE = true;

    public static final boolean DEBUG_ACTIONS_CACHE = true;

    public static long CACHE_TIME = 5l;

    public static final int ACTION_LOGIN = 1;
    public static final int ACTION_LOGOFF = 2;
    public static final int ACTION_LIST = 3;
    public static final int ACTION_REFRESH = 4;
    public static final int ACTION_LIST_SERVER = 5;
    public static final int ACTION_LIST_OLDER = 6;
    public static final int ACTION_SEARCH = 7;

    public static final String LOGOFF = "LOGOFF";

    public final static int TAB_CINZA = 0;
    public static int TAB_VERMELHA = 1;
    public static int TAB_LARANJA = 2;
    public static int TAB_VERDE = 3;
    public static int TAB_ROXO = 4;

    public static String URL_VISUALIZAR_AVALIACAO = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes";

    public static int MAX_ITEMS = 20;

    public static String SITUACAO_NAO_AVALIADO = "Pré-Cadastro";
    public static String SITUACAO_AVALIADO = "Avaliado";
    public static String SITUACAO_PROPOSTA = "Proposta";
    public static String SITUACAO_ESTOQUE = "Estoque";
    public static String SITUACAO_VENDIDO = "Vendido";

    public static String typeStatus = "pre-cadastro";

}
