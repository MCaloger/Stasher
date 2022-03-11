package com.caloger.stasher.Secret.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SecretModel {
    @Id
    @GeneratedValue()
    private Long id;
    private String code;
    private String message;

    public SecretModel(Long id, String code, String message) {
        this.id = id;
        this.code = code;
        this.message = message;
    }

    public SecretModel(String code, String message, String password) {
        this.code = code;
        this.message = message;
    }

    public SecretModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "SecretModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
