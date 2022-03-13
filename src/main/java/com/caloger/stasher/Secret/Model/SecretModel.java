package com.caloger.stasher.Secret.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class SecretModel {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(unique=true)
    private String code;

    private String message;
    private LocalTime localTime;

    public SecretModel() {
    }

    public SecretModel(Long id, String code, String message, LocalTime localTime) {
        this.id = id;
        this.code = code;
        this.message = message;
        this.localTime = localTime;
    }

    public SecretModel(String code, String message, LocalTime localTime) {
        this.code = code;
        this.message = message;
        this.localTime = localTime;
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

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
