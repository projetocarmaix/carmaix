package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 01/08/16.
 */
public class EstatisticaReturn {
    @SerializedName("avaliado")
    private EstatisticaRows avaliado;

    @SerializedName("estoque")
    private EstatisticaRows estoque;

    @SerializedName("vendido")
    private EstatisticaRows vendido;

    @SerializedName("fipe")
    private FipePassecarrosReturn fipe;

    @SerializedName("passecarros")
    private FipePassecarrosReturn passecarros;

    public EstatisticaRows getAvaliado() {
        return avaliado;
    }

    public void setAvaliado(EstatisticaRows avaliado) {
        this.avaliado = avaliado;
    }

    public EstatisticaRows getEstoque() {
        return estoque;
    }

    public void setEstoque(EstatisticaRows estoque) {
        this.estoque = estoque;
    }

    public EstatisticaRows getVendido() {
        return vendido;
    }

    public void setVendido(EstatisticaRows vendido) {
        this.vendido = vendido;
    }

    public FipePassecarrosReturn getFipe() {
        return fipe;
    }

    public void setFipe(FipePassecarrosReturn fipe) {
        this.fipe = fipe;
    }

    public FipePassecarrosReturn getPassecarros() {
        return passecarros;
    }

    public void setPassecarros(FipePassecarrosReturn passecarros) {
        this.passecarros = passecarros;
    }
}
