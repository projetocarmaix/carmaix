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
    public static final int ACTION_SEARCH_LIST = 8;
    public static final int ACTION_FILL = 9;
    public static final int ACTION_SEND_IMAGE_FILES = 10;
    public static final int  ACTION_SEND_DATA = 11;
    public static final int ACTION_AVALIAR = 12;
    public static final int ACTION_REVALIDAR = 13;
    public static final String LOGOFF = "LOGOFF";

    public final static int TAB_CINZA = 0;
    public static int TAB_VERMELHA = 1;
    public static int TAB_LARANJA = 2;
    public static int TAB_VERDE = 3;
    public static int TAB_ROXO = 4;

    public static String URL_VISUALIZAR_AVALIACAO = "https://apicarmaix1.websiteseguro.com/v1/avaliacoes";

    public static int MAX_ITEMS = 20;

    public static String SITUACAO_NAO_AVALIADO = "PrÃ©-Cadastro";
    public static String SITUACAO_AVALIADO = "Avaliado";
    public static String SITUACAO_PROPOSTA = "Proposta";
    public static String SITUACAO_ESTOQUE = "Estoque";
    public static String SITUACAO_VENDIDO = "Vendido";

    public final static String STATUS_NAO_AVALIADO = "pre-cadastro";
    public final static String STATUS_AVALIADO = "avaliado";
    public final static String STATUS_PROPOSTA = "proposta";
    public final static String STATUS_ESTOQUE = "estoque";
    public final static String STATUS_VENDIDO = "vendido";


    public final static Integer MARTELINHO = 160;
    public final static Integer PINTAR_PORTA = 300;
    public final static Integer PNEUS_13 = 150;
    public final static Integer PNEUS_R15 = 300;

    public final static String OPTION_ATACADO = "atacado";
    public final static String OPTION_VAREJO = "varejo";


    public final static String OPTION_GARANTIA_SIM = "S";
    public final static String OPTION_GARANTIA_NAO = "N";

    public final static String NO_IMAGE = "";

    public final static String SITUACAO_ENVIO_AVALIADO = "avaliado";
    public final static String SITUACAO_ENVIO_PRE_CADASTRO = "pre-cadastro";

}

