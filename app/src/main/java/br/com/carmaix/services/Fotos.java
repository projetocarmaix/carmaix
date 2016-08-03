package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 02/08/16.
 */
public class Fotos {
    @SerializedName("frente")
    private String frente;

    @SerializedName("traseira")
    private String traseira;

    @SerializedName("lat_esquerda")
    private String lat_esquerda;

    @SerializedName("lat_direita")
    private String lat_direita;

    @SerializedName("interior")
    private String interior;

    @SerializedName("odometro")
    private String odometro;

    @SerializedName("pneu")
    private String pneu;

    @SerializedName("detalhe")
    private String detalhe;

    @SerializedName("estepe")
    private String estepe;

    @SerializedName("documento")
    private String documento;

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }

    public String getTraseira() {
        return traseira;
    }

    public void setTraseira(String traseira) {
        this.traseira = traseira;
    }

    public String getLat_esquerda() {
        return lat_esquerda;
    }

    public void setLat_esquerda(String lat_esquerda) {
        this.lat_esquerda = lat_esquerda;
    }

    public String getLat_direita() {
        return lat_direita;
    }

    public void setLat_direita(String lat_direita) {
        this.lat_direita = lat_direita;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getOdometro() {
        return odometro;
    }

    public void setOdometro(String odometro) {
        this.odometro = odometro;
    }

    public String getPneu() {
        return pneu;
    }

    public void setPneu(String pneu) {
        this.pneu = pneu;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getEstepe() {
        return estepe;
    }

    public void setEstepe(String estepe) {
        this.estepe = estepe;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
