package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 10/06/2016.
 */
public class TokenReturn {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
