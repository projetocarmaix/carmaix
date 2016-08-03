package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 02/08/16.
 */
public class InformacoesAvaliacaoReturn {

    @SerializedName("id")
    private String id;

    @SerializedName("data")
    private String data;

    @SerializedName("avaliador_id")
    private String avaliador_id;

    @SerializedName("vendedor_id")
    private String vendedor_id;

    @SerializedName("empresa")
    private String empresa;

    @SerializedName("cliente")
    private Cliente cliente;

    @SerializedName("observacoes")
    private String observacoes;

    @SerializedName("observacoes_adicionais")
    private String observacoes_adicionais;

    @SerializedName("motivo_avaliacao")
    private String motivo_avaliacao;

    @SerializedName("tipo_compra")
    private String tipo_compra;

    @SerializedName("veiculo")
    private Veiculo veiculo;

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

    public String getAvaliador_id() {
        return avaliador_id;
    }

    public void setAvaliador_id(String avaliador_id) {
        this.avaliador_id = avaliador_id;
    }

    public String getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(String vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getObservacoes_adicionais() {
        return observacoes_adicionais;
    }

    public void setObservacoes_adicionais(String observacoes_adicionais) {
        this.observacoes_adicionais = observacoes_adicionais;
    }

    public String getMotivo_avaliacao() {
        return motivo_avaliacao;
    }

    public void setMotivo_avaliacao(String motivo_avaliacao) {
        this.motivo_avaliacao = motivo_avaliacao;
    }

    public String getTipo_compra() {
        return tipo_compra;
    }

    public void setTipo_compra(String tipo_compra) {
        this.tipo_compra = tipo_compra;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
