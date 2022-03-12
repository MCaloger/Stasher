package com.caloger.stasher.Secured.Model.Read;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SecuredReadRequestModel {

    @NotNull(message="Password must not be blank.")
    @NotBlank(message="Password must not be blank.")
    @NotEmpty(message="Password must not be blank.")
    private String password;

    public SecuredReadRequestModel() {
    }

    public SecuredReadRequestModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
