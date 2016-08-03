package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 02/08/16.
 */
public class Mecanica {
    @SerializedName("mec_motor")
    private String mec_motor;

    @SerializedName("mec_susp_dianteira")
    private String mec_susp_dianteira;

    @SerializedName("mec_homocinetica")
    private String mec_homocinetica;

    @SerializedName("mec_cambio")
    private String mec_cambio;

    @SerializedName("mec_susp_traseira")
    private String mec_susp_traseira;

    @SerializedName("mec_rolamentos")
    private String mec_rolamentos;

    @SerializedName("mec_embreagem")
    private String mec_embreagem;

    @SerializedName("mec_cx_direcao")
    private String mec_cx_direcao;

    @SerializedName("mec_pneus_diant")
    private String mec_pneus_diant;

    @SerializedName("mec_freios")
    private String mec_freios;

    @SerializedName("mec_escapamento")
    private String mec_escapamento;

    @SerializedName("mec_pneus_tras")
    private String mec_pneus_tras;

    @SerializedName("mec_diferencial")
    private String mec_diferencial;

    public String getMec_motor() {
        return mec_motor;
    }

    public void setMec_motor(String mec_motor) {
        this.mec_motor = mec_motor;
    }

    public String getMec_susp_dianteira() {
        return mec_susp_dianteira;
    }

    public void setMec_susp_dianteira(String mec_susp_dianteira) {
        this.mec_susp_dianteira = mec_susp_dianteira;
    }

    public String getMec_homocinetica() {
        return mec_homocinetica;
    }

    public void setMec_homocinetica(String mec_homocinetica) {
        this.mec_homocinetica = mec_homocinetica;
    }

    public String getMec_cambio() {
        return mec_cambio;
    }

    public void setMec_cambio(String mec_cambio) {
        this.mec_cambio = mec_cambio;
    }

    public String getMec_susp_traseira() {
        return mec_susp_traseira;
    }

    public void setMec_susp_traseira(String mec_susp_traseira) {
        this.mec_susp_traseira = mec_susp_traseira;
    }

    public String getMec_rolamentos() {
        return mec_rolamentos;
    }

    public void setMec_rolamentos(String mec_rolamentos) {
        this.mec_rolamentos = mec_rolamentos;
    }

    public String getMec_embreagem() {
        return mec_embreagem;
    }

    public void setMec_embreagem(String mec_embreagem) {
        this.mec_embreagem = mec_embreagem;
    }

    public String getMec_cx_direcao() {
        return mec_cx_direcao;
    }

    public void setMec_cx_direcao(String mec_cx_direcao) {
        this.mec_cx_direcao = mec_cx_direcao;
    }

    public String getMec_freios() {
        return mec_freios;
    }

    public void setMec_freios(String mec_freios) {
        this.mec_freios = mec_freios;
    }

    public String getMec_pneus_diant() {
        return mec_pneus_diant;
    }

    public void setMec_pneus_diant(String mec_pneus_diant) {
        this.mec_pneus_diant = mec_pneus_diant;
    }

    public String getMec_escapamento() {
        return mec_escapamento;
    }

    public void setMec_escapamento(String mec_escapamento) {
        this.mec_escapamento = mec_escapamento;
    }

    public String getMec_pneus_tras() {
        return mec_pneus_tras;
    }

    public void setMec_pneus_tras(String mec_pneus_tras) {
        this.mec_pneus_tras = mec_pneus_tras;
    }

    public String getMec_diferencial() {
        return mec_diferencial;
    }

    public void setMec_diferencial(String mec_diferencial) {
        this.mec_diferencial = mec_diferencial;
    }
}
