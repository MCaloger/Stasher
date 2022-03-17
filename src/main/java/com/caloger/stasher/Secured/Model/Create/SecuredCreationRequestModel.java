package com.caloger.stasher.Secured.Model.Create;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalTime;
import javax.validation.constraints.*;

public class SecuredCreationRequestModel {

    @NotNull(message="Message must not be blank.")
    @NotBlank(message="Message must not be blank.")
    @NotEmpty(message="Message must not be blank.")
    @Size(min=1, max=1024, message="Message must be between 1 and 1024 characters.")
    private String message;

    @NotNull(message="Password must not be blank.")
    @NotBlank(message="Password must not be blank.")
    @NotEmpty(message="Password must not be blank.")
    @Size(min=1, max=128, message="Password must be no longer than 128 characters.")
    private String password;

    private LocalTime expiry;

    public SecuredCreationRequestModel() {
    }

    public SecuredCreationRequestModel(String message, String password) {
        this.message = message;
        this.password = password;
        this.expiry = LocalTime.now().plusHours(1);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
