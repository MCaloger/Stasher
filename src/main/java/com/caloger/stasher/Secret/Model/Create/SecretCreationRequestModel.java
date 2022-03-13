package com.caloger.stasher.Secret.Model.Create;

public class SecretCreationRequestModel {
    String message;

    public SecretCreationRequestModel() {
    }

    public SecretCreationRequestModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
