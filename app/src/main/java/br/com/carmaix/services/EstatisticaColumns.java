package br.com.carmaix.services;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 01/08/16.
 */
public class EstatisticaColumns {
    @SerializedName("unidade")
    @Nullable
    private String unidade;

    @SerializedName("grupo")
    @Nullable
    private String grupo;

    @SerializedName("total")
    @Nullable
    private String total;

    @Nullable
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(@Nullable String unidade) {
        this.unidade = unidade;
    }

    @Nullable
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(@Nullable String grupo) {
        this.grupo = grupo;
    }

    @Nullable
    public String getTotal() {
        return total;
    }

    public void setTotal(@Nullable String total) {
        this.total = total;
    }
}
