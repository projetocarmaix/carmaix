package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 02/08/16.
 */
public class Veiculo {
    @SerializedName("placa")
    private String placa;

    @SerializedName("modelo_id")
    private String modelo_id;

    @SerializedName("categoria_id")
    private String categoria_id;

    @SerializedName("marca_id")
    private String marca_id;

    @SerializedName("ano_fabricacao")
    private String ano_fabricacao;

    @SerializedName("ano_modelo")
    private String ano_modelo;

    @SerializedName("combustivel_id")
    private String combustivel_id;

    @SerializedName("portas")
    private String portas;

    @SerializedName("cor")
    private String cor;

    @SerializedName("chassi")
    private String chassi;

    @SerializedName("renavam")
    private String renavam;

    @SerializedName("acessorio")
    private String acessorio;

    @SerializedName("aro")
    private String aro;

    @SerializedName("km")
    private String km;

    @SerializedName("garantia_fabrica")
    private String garantia_fabrica;

    @SerializedName("nota")
    private String nota;

    @SerializedName("classificacao")
    private String classificacao;

    @SerializedName("valor")
    private String valor;

    @SerializedName("franquia_reparos")
    private String franquia_reparos;

    @SerializedName("opcionais")
    private String[] opcionais;

    @SerializedName("itens")
    private String[] itens;


    @SerializedName("mecanica")
    private Mecanica mecanica;

    @SerializedName("estrutura")
    private Estrutura estrutura;

    @SerializedName("colisao")
    private String[] colisao;


    @SerializedName("fotos")
    private Fotos fotos;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo_id() {
        return modelo_id;
    }

    public void setModelo_id(String modelo_id) {
        this.modelo_id = modelo_id;
    }

    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getMarca_id() {
        return marca_id;
    }

    public void setMarca_id(String marca_id) {
        this.marca_id = marca_id;
    }

    public String getAno_fabricacao() {
        return ano_fabricacao;
    }

    public void setAno_fabricacao(String ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }

    public String getAno_modelo() {
        return ano_modelo;
    }

    public void setAno_modelo(String ano_modelo) {
        this.ano_modelo = ano_modelo;
    }

    public String getCombustivel_id() {
        return combustivel_id;
    }

    public void setCombustivel_id(String combustivel_id) {
        this.combustivel_id = combustivel_id;
    }

    public String getPortas() {
        return portas;
    }

    public void setPortas(String portas) {
        this.portas = portas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(String acessorio) {
        this.acessorio = acessorio;
    }

    public String getAro() {
        return aro;
    }

    public void setAro(String aro) {
        this.aro = aro;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getGarantia_fabrica() {
        return garantia_fabrica;
    }

    public void setGarantia_fabrica(String garantia_fabrica) {
        this.garantia_fabrica = garantia_fabrica;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFranquia_reparos() {
        return franquia_reparos;
    }

    public void setFranquia_reparos(String franquia_reparos) {
        this.franquia_reparos = franquia_reparos;
    }

    public String[] getOpcionais() {
        return opcionais;
    }

    public void setOpcionais(String[] opcionais) {
        this.opcionais = opcionais;
    }

    public String[] getItens() {
        return itens;
    }

    public void setItens(String[] itens) {
        this.itens = itens;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }

    public Estrutura getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(Estrutura estrutura) {
        this.estrutura = estrutura;
    }

    public String[] getColisao() {
        return colisao;
    }

    public void setColisao(String[] colisao) {
        this.colisao = colisao;
    }

    public Fotos getFotos() {
        return fotos;
    }

    public void setFotos(Fotos fotos) {
        this.fotos = fotos;
    }
}
