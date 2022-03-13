package com.caloger.stasher.Secret.Model.Read;

public class SecretReadResponseModel {
    String message;

    public SecretReadResponseModel(String message) {
        this.message = message;
    }

    public SecretReadResponseModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
