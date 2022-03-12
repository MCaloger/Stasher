package com.caloger.stasher.Secured.Model;

import com.caloger.stasher.Encryption.Model.EncryptedProperties;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class SecuredModel {
    @Id
    @GeneratedValue
    Long id;

    private String code;
    private String encryptedMessage;
    private byte[] initializationVectorSeed;
    private String salt;
    private LocalTime expiry;

    public SecuredModel() {
    }

    public SecuredModel(Long id, String code, String encryptedMessage, byte[] initializationVectorSeed, String salt, LocalTime expiry) {
        this.id = id;
        this.code = code;
        this.encryptedMessage = encryptedMessage;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;
        this.expiry = expiry;
    }

    public SecuredModel(String code, String encryptedMessage, byte[] initializationVectorSeed, String salt, LocalTime expiry) {
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

    public LocalTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalTime expiry) {
        this.expiry = expiry;
    }
}
