package br.com.carmaix.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * A simple demonstration object we are creating and persisting to the database.
 */
@DatabaseTable(tableName = "server_information")
public class LoginTable implements Serializable {

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String userEmail;

    @DatabaseField
    String token;

    @DatabaseField
    String userId;

    @DatabaseField
    String userName;

    @DatabaseField
    String companyName;

    @DatabaseField
    String companyLogo;

    @DatabaseField
    Boolean revalidar;

    @DatabaseField
    Boolean visualizar;

    @DatabaseField
    Boolean avaliar;

    @DatabaseField
    Boolean editar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
