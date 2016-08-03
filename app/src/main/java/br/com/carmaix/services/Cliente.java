package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fernando on 02/08/16.
 */
public class Cliente {
    @SerializedName("nome")
    private String nome;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("estado_id")
    private String estado_id;

    @SerializedName("cidade_id")
    private String cidade_id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(String estado_id) {
        this.estado_id = estado_id;
    }

    public String getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(String cidade_id) {
        this.cidade_id = cidade_id;
    }
}
