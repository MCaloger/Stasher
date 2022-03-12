package com.caloger.stasher.Secured.Model.Read;

public class SecuredReadResponseModel {
    private String message;

    public SecuredReadResponseModel(String message) {
        this.message = message;
    }

    public SecuredReadResponseModel() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
