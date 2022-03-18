package com.caloger.stasher.Secured.Model;

import com.caloger.stasher.Encryption.Model.EncryptedProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

@Entity
public class SecuredModel {
    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    private String code;

    private String encryptedMessage;
    private byte[] initializationVectorSeed;
    private String salt;
    private LocalDateTime expiry;

    public SecuredModel() {
    }

    public SecuredModel(Long id, String code, String encryptedMessage, byte[] initializationVectorSeed, String salt, LocalDateTime expiry) {
        this.id = id;
        this.code = code;
        this.encryptedMessage = encryptedMessage;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;
        this.expiry = expiry;
    }

    public SecuredModel(String code, String encryptedMessage, byte[] initializationVectorSeed, String salt, LocalDateTime expiry) {
        this.code = code;
        this.encryptedMessage = encryptedMessage;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;
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

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public byte[] getInitializationVectorSeed() {
        return initializationVectorSeed;
    }

    public void setInitializationVectorSeed(byte[] initializationVectorSeed) {
        this.initializationVectorSeed = initializationVectorSeed;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
}
