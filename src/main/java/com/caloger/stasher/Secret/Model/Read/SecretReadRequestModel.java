package com.caloger.stasher.Secret.Model.Read;

public class SecretReadRequestModel {
    String code;

    public SecretReadRequestModel() {
    }

    public SecretReadRequestModel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
