package com.grupo8.jwt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    private String status;
    private String message;
    @JsonProperty("ID_USUARIO")
    private String ID_USUARIO;
    
    public LoginResponse(String status, String message, String iD_USUARIO) {
        this.status = status;
        this.message = message;
        ID_USUARIO = iD_USUARIO;
    }
    public LoginResponse() {
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getID_USUARIO() {
        return ID_USUARIO;
    }
    public void setID_USUARIO(String iD_USUARIO) {
        ID_USUARIO = iD_USUARIO;
    }

    
}
