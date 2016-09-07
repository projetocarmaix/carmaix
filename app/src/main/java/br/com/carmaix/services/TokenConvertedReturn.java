package br.com.carmaix.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 10/06/2016.
 */
public class TokenConvertedReturn {

    @SerializedName("userName")
    private String userName;
    @SerializedName("userContrato")
    private String userContrato;
    @SerializedName("userSetor")
    private String userSetor;
    @SerializedName("userId")
    private String userId;
    @SerializedName("userAcl")
    private UserAcl userAcl;

    public UserAcl getUserAcl() {
        return userAcl;
    }

    public void setUserAcl(UserAcl userAcl) {
        this.userAcl = userAcl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserContrato() {
        return userContrato;
    }

    public void setUserContrato(String userContrato) {
        this.userContrato = userContrato;
    }

    public String getUserSetor() {
        return userSetor;
    }

    public void setUserSetor(String userSetor) {
        this.userSetor = userSetor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
