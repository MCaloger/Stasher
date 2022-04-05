package com.caloger.stasher.Secured.Model;

import com.caloger.stasher.Encryption.Model.EncryptedPropertiesModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SecuredModel {
    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    private String code;

    @Embedded
    EncryptedPropertiesModel encryptedProperties;

    private LocalDateTime expiry;

    public SecuredModel() {
    }

    public SecuredModel(Long id, String code, String encryptedMessage, byte[] initializationVectorSeed, String salt, LocalDateTime expiry) {
        this.id = id;
        this.code = code;
        this.encryptedProperties = new EncryptedPropertiesModel(encryptedMessage, initializationVectorSeed,
                salt);
    }

    public SecuredModel(String code, String encryptedMessage, byte[] initializationVectorSeed, String salt, LocalDateTime expiry) {
        this.code = code;
        this.encryptedProperties = new EncryptedPropertiesModel(encryptedMessage, initializationVectorSeed,
                salt);
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

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }

    public EncryptedPropertiesModel getEncryptedProperties() {
        return encryptedProperties;
    }

    public void setEncryptedProperties(EncryptedPropertiesModel encryptedProperties) {
        this.encryptedProperties = encryptedProperties;
    }

    @Override
    public String toString() {
        return "SecuredModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", encryptedProperties=" + encryptedProperties +
                ", expiry=" + expiry +
                '}';
    }
}
