package com.caloger.stasher.Secret.Model.Create;

public class SecretCreationResponseModel {
    private String code;
    private String uri;

    public SecretCreationResponseModel(String domain, String code) {
        this.code = code;
        this.uri = new StringBuilder().append(domain).append("/secret/code/").append(code).toString();
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
}
