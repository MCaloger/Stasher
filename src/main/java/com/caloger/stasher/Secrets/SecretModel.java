package com.caloger.stasher.Secrets;

public class SecretModel {
    private int id;
    private String code;
    private String message;
    private String password;

    public SecretModel(int id, String code, String message, String password) {
        this.id = id;
        this.code = code;
        this.message = message;
        this.password = password;
    }

    public SecretModel(String code, String message, String password) {
        this.code = code;
        this.message = message;
        this.password = password;
    }

    public SecretModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "SecretModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
