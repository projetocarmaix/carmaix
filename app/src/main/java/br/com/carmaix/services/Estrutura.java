package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 02/08/16.
 */
public class Estrutura {
    @SerializedName("est_lataria")
    private String est_lataria;

    @SerializedName("est_parachoque_diant")
    private String est_parachoque_diant;

    @SerializedName("est_pintura")
    private String est_pintura;

    @SerializedName("est_parachoque_tras")
    private String est_parachoque_tras;

    @SerializedName("est_carroceria")
    private String est_carroceria;

    @SerializedName("est_parabrisa")
    private String est_parabrisa;

    @SerializedName("est_porta_malas")
    private String est_porta_malas;

    @SerializedName("est_estofamento")
    private String est_estofamento;

    @SerializedName("est_motor")
    private String est_motor;

    @SerializedName("est_farol")
    private String est_farol;

    public String getEst_lataria() {
        return est_lataria;
    }

    public void setEst_lataria(String est_lataria) {
        this.est_lataria = est_lataria;
    }

    public String getEst_parachoque_diant() {
        return est_parachoque_diant;
    }

    public void setEst_parachoque_diant(String est_parachoque_diant) {
        this.est_parachoque_diant = est_parachoque_diant;
    }

    public String getEst_pintura() {
        return est_pintura;
    }

    public void setEst_pintura(String est_pintura) {
        this.est_pintura = est_pintura;
    }

    public String getEst_parachoque_tras() {
        return est_parachoque_tras;
    }

    public void setEst_parachoque_tras(String est_parachoque_tras) {
        this.est_parachoque_tras = est_parachoque_tras;
    }

    public String getEst_carroceria() {
        return est_carroceria;
    }

    public void setEst_carroceria(String est_carroceria) {
        this.est_carroceria = est_carroceria;
    }

    public String getEst_parabrisa() {
        return est_parabrisa;
    }

    public void setEst_parabrisa(String est_parabrisa) {
        this.est_parabrisa = est_parabrisa;
    }

    public String getEst_porta_malas() {
        return est_porta_malas;
    }

    public void setEst_porta_malas(String est_porta_malas) {
        this.est_porta_malas = est_porta_malas;
    }

    public String getEst_motor() {
        return est_motor;
    }

    public void setEst_motor(String est_motor) {
        this.est_motor = est_motor;
    }

    public String getEst_estofamento() {
        return est_estofamento;
    }

    public void setEst_estofamento(String est_estofamento) {
        this.est_estofamento = est_estofamento;
    }

    public String getEst_farol() {
        return est_farol;
    }

    public void setEst_farol(String est_farol) {
        this.est_farol = est_farol;
    }
}
