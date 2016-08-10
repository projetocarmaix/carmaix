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

    public static ArrayList<ValueLabelDefault> listModelosMarca(Context context, String id) throws Exception {
        return getVersionRelease().listModelosMarca(context,id);
    }

    public static ArrayList<ValueLabelDefault> listAnoFabricacao(Context context, String id) throws Exception {
        return getVersionRelease().listAnoFabricacao(context,id);
    }

    public static ArrayList<ValueLabelDefault> listAnoModelo(Context context, String id, String ano) throws Exception {
        return getVersionRelease().listAnoModelo(context,id,ano);
    }

    public static ArrayList<ValueLabelDefault> listOpcionais(Context context) throws Exception {
        return getVersionRelease().listOpcionais(context);
    }

    public static ArrayList<ValueLabelDefault> listCidades(Context context, String uf) throws Exception {
        return getVersionRelease().listCidades(context, uf);
    }

    public static ArrayList<ValueLabelDefault> listCombustiveisModelos(Context context, String modelo) throws Exception {
        return getVersionRelease().listCombustiveisModelos(context, modelo);
    }

    public static ArrayList<ValueLabelDefault> listAnoCombustivel(Context context, String modelo, String combustivel) throws Exception {
        return getVersionRelease().listAnoCombustivel(context, modelo, combustivel);
    }

    public static ValorMedioReturn getValorMedio(Context context, String modelo,String ano, String combustivel) throws Exception {
        return getVersionRelease().getValorMedio(context, modelo, ano,  combustivel);
    }

    public static ArrayList<ValueLabelDefault> listMotivoAvaliacao(Context context) throws Exception {
        return getVersionRelease().listMotivoAvaliacao(context);
    }

    public static ArrayList<ValueLabelDefault> listClassificacoes(Context context) throws Exception {
        return getVersionRelease().listClassificacoes(context);
    }

    public static InformacoesAvaliacaoReturn listInformacaoAvaliacao(Context context, String idAvaliacao) throws Exception {
        return getVersionRelease().listInformacaoAvaliacao(context, idAvaliacao);
    }

    public static EstatisticaReturn getEstatistica(Context context, String modelo, String ano, String combustivel) throws Exception {
        return getVersionRelease().getEstatistica(context, modelo,ano,combustivel);
    }

    public static UserReturn getAvaliador(Context context, String idUsuario) throws Exception {
        return getVersionRelease().getAvaliador(context, idUsuario);
    }

    public static String sendEsqueciSenha(Context context, String email) throws Exception {
        return getVersionRelease().sendEsqueciSenha(context, email);
    }
}