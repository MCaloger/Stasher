package com.caloger.stasher.Secret.Model.Create;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class SecretCreationRequestModel {
    @Size(min=1, max=1024, message="Message must not be blank or larger than 1024 characters.")
    String message;

    @Min(value=0, message="Can't be less than 0 hours.")
    @Max(value=23, message="Can't be more than 23 hours.")
    private int expirationHours;

    @Min(value=0, message="Can't be less than 0 minutes.")
    @Max(value=59, message="Can't be more than 59 minutes.")
    private int expirationMinutes;

    private LocalDateTime expiry;

    public SecretCreationRequestModel() {
    }

    public SecretCreationRequestModel(String message, int expirationHours, int expirationMinutes) {
        this.message = message;
        this.expirationHours = expirationHours;
        this.expirationMinutes = expirationMinutes;
        this.expiry = LocalDateTime.now().plusHours(expirationHours).plusMinutes(expirationMinutes);
    }

    public String getMessage() {
        return message;
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

    public void setMessage(String message) {
        this.message = message;
    }
}
