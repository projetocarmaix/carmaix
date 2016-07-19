package br.com.carmaix.utils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by fernando on 19/06/16.
 */
public class ValueLabelDefault {

    @SerializedName("value")
    private String id;
    @SerializedName("label")
    private String descricao;

    protected String valueDefault = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValueDefault() {
        return valueDefault;
    }

    public void setValueDefault(String valueDefault) {
        this.valueDefault = valueDefault;
    }
    @Override
    public String toString() {
        return descricao;
    }
}
