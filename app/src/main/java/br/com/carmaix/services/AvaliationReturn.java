package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 10/06/2016.
 */
public class AvaliationReturn {

    @SerializedName("id")
    private String id;

    @SerializedName("data")
    private String data;

    @SerializedName("nome")
    private String nome;

    @SerializedName("placa")
    private String placa;

    @SerializedName("modelo")
    private String modelo;

    @SerializedName("marca")
    private String marca;

    @SerializedName("ano")
    private String ano;

    @SerializedName("classe")
    private String classe;

    @SerializedName("valor")
    private String valor;

    @SerializedName("situacao")
    private String situacao;

    @SerializedName("vendedor_id")
    private String vendedor_id;

    @SerializedName("validade")
    private String validade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(String vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
