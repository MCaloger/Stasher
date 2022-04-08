package com.caloger.stasher.Secret.Model.Create;

public class SecretCreationResponseModel {
    private String code;
    private String uri;
    private int expirationHours;
    private int expirationMinutes;


    public SecretCreationResponseModel(String domain, String code, int expirationHours, int expirationMinutes) {
        this.code = code;
        this.uri = new StringBuilder().append(domain).append("/secret/code/").append(code).toString();
        this.expirationHours = expirationHours;
        this.expirationMinutes = expirationMinutes;
    }

    public SecretCreationResponseModel() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getExpirationHours() {
        return expirationHours;
    }

    public void setExpirationHours(int expirationHours) {
        this.expirationHours = expirationHours;
    }

    public int getExpirationMinutes() {
        return expirationMinutes;
    }

    public void setExpirationMinutes(int expirationMinutes) {
        this.expirationMinutes = expirationMinutes;
    }
}
