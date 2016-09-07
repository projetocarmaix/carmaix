package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 10/06/2016.
 */
public class AvaliacaoAcl {

    @SerializedName("revalidar")
    private Boolean revalidar;

    @SerializedName("visualizar")
    private Boolean visualizar;

    @SerializedName("avaliar")
    private Boolean avaliar;

    @SerializedName("editar")
    private Boolean editar;

    public Boolean getRevalidar() {
        return revalidar;
    }

    public void setRevalidar(Boolean revalidar) {
        this.revalidar = revalidar;
    }

    public Boolean getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(Boolean visualizar) {
        this.visualizar = visualizar;
    }

    public Boolean getAvaliar() {
        return avaliar;
    }

    public void setAvaliar(Boolean avaliar) {
        this.avaliar = avaliar;
    }

    public Boolean getEditar() {
        return editar;
    }

    public void setEditar(Boolean editar) {
        this.editar = editar;
    }
}
