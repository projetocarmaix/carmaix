package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 19/06/16.
 */
public class UserReturn {

    @SerializedName("id")
    private String id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("nome_empresa")
    private String nome_empresa;
    @SerializedName("logo_empresa")
    private String logo_empresa;

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public String getLogo_empresa() {
        return logo_empresa;
    }
}
