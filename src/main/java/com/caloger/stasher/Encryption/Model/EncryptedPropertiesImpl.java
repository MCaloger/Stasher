package com.caloger.stasher.Encryption.Model;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptedPropertiesImpl implements EncryptedProperties {

    private String encryptedMessage;
    private SecretKey secretKey;
    private byte[] initializationVectorSeed;
    private String salt;

    public EncryptedPropertiesImpl(String encryptedMessage, SecretKey key, byte[] initializationVectorSeed, String salt) {
        this.encryptedMessage = encryptedMessage;
        this.secretKey = key;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;

    }

    public EncryptedPropertiesImpl(String encryptedMessage, byte[] initializationVectorSeed, String salt) {
        this.encryptedMessage = encryptedMessage;
        this.initializationVectorSeed = initializationVectorSeed;
        this.salt = salt;
    }

    @Override
    public SecretKey getSecretKey() {
        return secretKey;
    }

    @Override
    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public byte[] getInitializationVectorSeed() {
        return initializationVectorSeed;
    }

    public void setInitializationVectorSeed(byte[] initializationVectorSeed) {
        this.initializationVectorSeed = initializationVectorSeed;
    }

    @Override
    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    @Override
    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }
}
