package com.caloger.stasher.Secret.Model.Create;

import javax.validation.constraints.Size;

public class SecretCreationRequestModel {
    @Size(min=1, max=1024, message="Message must not be blank or larger than 1024 characters.")
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
