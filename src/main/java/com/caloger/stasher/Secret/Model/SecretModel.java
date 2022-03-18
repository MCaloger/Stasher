package com.caloger.stasher.Secret.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class SecretModel {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(unique=true)
    private String code;

    @Size(max=1024)
    private String message;

    private LocalDateTime expiry;

    public SecretModel() {
    }

    public SecretModel(Long id, String code, String message, LocalDateTime expiry) {
        this.id = id;
        this.code = code;
        this.message = message;
        this.expiry = expiry;
    }

    public SecretModel(String code, String message, LocalDateTime expiry) {
        this.code = code;
        this.message = message;
        this.expiry = expiry;
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

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
}
