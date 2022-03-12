package com.caloger.stasher.Secured.Model.Create;

public class SecuredCreationResponseModel {

    private String code;
    private String uri;

    public SecuredCreationResponseModel(String domain, String code) {
        this.code = code;
        this.uri = new StringBuilder().append(domain).append("/api/secured/code/").append(code).toString();
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
