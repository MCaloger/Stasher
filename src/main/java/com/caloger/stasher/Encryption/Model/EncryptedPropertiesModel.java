package com.caloger.stasher.Encryption.Model;

import javax.crypto.SecretKey;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Arrays;

@Embeddable
public class EncryptedPropertiesModel {

    @Column(columnDefinition = "TEXT")
    private String encryptedMessage;
    private SecretKey secretKey;
    private byte[] initializationVectorSeed;
    private String salt;

    public EncryptedPropertiesModel() {
    }

    public EncryptedPropertiesModel(String encryptedMessage, SecretKey key, byte[] initializationVectorSeed, String salt) {
        this.encryptedMessage = encryptedMessage;
        this.secretKey = key;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;

    }

    public EncryptedPropertiesModel(String encryptedMessage, byte[] initializationVectorSeed, String salt) {
        this.encryptedMessage = encryptedMessage;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public byte[] getInitializationVectorSeed() {
        return initializationVectorSeed;
    }

    public void setInitializationVectorSeed(byte[] initializationVectorSeed) {
        this.initializationVectorSeed = initializationVectorSeed;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String toString() {
        return "EncryptedPropertiesImpl{" +
                "encryptedMessage='" + encryptedMessage + '\'' +
                ", secretKey=" + secretKey +
                ", initializationVectorSeed=" + Arrays.toString(initializationVectorSeed) +
                ", salt='" + salt + '\'' +
                '}';
    }
}
