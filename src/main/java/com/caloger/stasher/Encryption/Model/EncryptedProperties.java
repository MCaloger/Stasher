package com.caloger.stasher.Encryption.Model;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public interface EncryptedProperties {
    SecretKey getSecretKey();

    void setSecretKey(SecretKey secretKey);

    byte[] getInitializationVectorSeed();

    void setInitializationVectorSeed(byte[] initializationVector);

    String getEncryptedMessage();

    void setEncryptedMessage(String encryptedMessage);

    String getSalt();

    void setSalt(String salt);
}
