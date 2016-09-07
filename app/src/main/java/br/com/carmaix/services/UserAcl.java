package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 10/06/2016.
 */
public class UserAcl {

    @SerializedName("avaliacao")
    private AvaliacaoAcl avaliacao;

    public AvaliacaoAcl getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoAcl avaliacao) {
        this.avaliacao = avaliacao;
    }
}
