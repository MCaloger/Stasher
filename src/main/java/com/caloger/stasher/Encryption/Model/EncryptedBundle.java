package com.caloger.stasher.Encryption.Model;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptedBundle {

    private String encryptedMessage;
    private SecretKey secretKey;
    private IvParameterSpec initializationVector;
    private String salt;

    public EncryptedBundle(String encryptedMessage, SecretKey key, IvParameterSpec initializationVector, String salt) {
        this.encryptedMessage = encryptedMessage;
        this.secretKey = key;
        this.initializationVector = initializationVector;
        this.salt = salt;

    }

    public EncryptedBundle(String encryptedMessage, IvParameterSpec initializationVector, String salt) {
        this.encryptedMessage = encryptedMessage;
        this.initializationVector = initializationVector;
        this.salt = salt;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public IvParameterSpec getInitializationVector() {
        return initializationVector;
    }

    public void setInitializationVector(IvParameterSpec initializationVector) {
        this.initializationVector = initializationVector;
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
}
