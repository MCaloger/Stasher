package com.caloger.stasher.Secured.Model.Create;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
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

    @Min(value=0, message="Can't be less than 0 hours.")
    @Max(value=23, message="Can't be more than 23 hours.")
    private int expirationHours;

    @Min(value=0, message="Can't be less than 0 minutes.")
    @Max(value=59, message="Can't be more than 59 minutes.")
    private int expirationMinutes;

    private LocalDateTime expiry;

    public SecuredCreationRequestModel() {
    }

    public SecuredCreationRequestModel(String message, String password, int expirationHours, int expirationMinutes) {
        this.message = message;
        this.password = password;
        this.expirationHours = expirationHours;
        this.expirationMinutes = expirationMinutes;
        this.expiry = LocalDateTime.now().plusHours(expirationHours).plusMinutes(expirationMinutes);
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

    public int getExpirationHours() {
        return expirationHours;
    }

    public void setExpirationHours(int expirationHours) {
        this.expirationHours = expirationHours;
        this.expiry = LocalDateTime.now().plusHours(expirationHours).plusMinutes(expirationMinutes);
    }

    public int getExpirationMinutes() {
        return expirationMinutes;
    }

    public void setExpirationMinutes(int expirationMinutes) {
        this.expirationMinutes = expirationMinutes;
        this.expiry = LocalDateTime.now().plusHours(expirationHours).plusMinutes(expirationMinutes);
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "SecuredCreationRequestModel{" +
                "message='" + message + '\'' +
                ", password='" + password + '\'' +
                ", expiry=" + expiry +
                '}';
    }
}
