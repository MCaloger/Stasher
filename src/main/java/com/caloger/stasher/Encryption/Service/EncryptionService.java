package com.caloger.stasher.Encryption.Service;

import com.caloger.stasher.Encryption.Model.EncryptedBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    @Value("${KEY_LENGTH}")
    private int KEY_LENGTH;

    @Value("${ITERATION_COUNT}")
    private int ITERATION_COUNT;

    @Value("${ALGORITHM}")
    private String ALGORITHM;

    private SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(this.KEY_LENGTH);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    private SecretKey generateSecretKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), this.ITERATION_COUNT, this.KEY_LENGTH);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    private IvParameterSpec generateInitializationVector() {
        byte[] initializationVector = new byte[16];
        new SecureRandom().nextBytes(initializationVector);
        return new IvParameterSpec(initializationVector);
    }

    private String generateSalt() {
        byte[] salt = new byte[KEY_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt.toString();
    }

    private String encrypt(String input, SecretKey key,
                                 IvParameterSpec iv) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    private String decrypt(String cipherText, SecretKey key,
                                 IvParameterSpec initializationVector) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, initializationVector);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    /**
     * Encrypts provided message wusing provided password in key.
     * @param message
     * @param unencryptedPassword
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public EncryptedBundle encryptMessage(String message, String unencryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Generate salt
        String salt = generateSalt();

        // Generate key combining password and generated salt
        SecretKey secretKey = generateSecretKeyFromPassword(unencryptedPassword, salt);

        // Generate an initialization vector
        IvParameterSpec initializationVector = generateInitializationVector();

        // Encrypt the message
        String encryptedMessage = encrypt(message, secretKey, initializationVector);

        // Return the encrtyped bundle
        return new EncryptedBundle(encryptedMessage, secretKey, initializationVector, salt);
    }

    /**
     * Decrypt with provided password and EncryptedBundle.
     * @param password
     * @param encryptedBundle
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public String decryptMessage(String password, EncryptedBundle encryptedBundle) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        // Build key from provided password and saved salt
        SecretKey secretKey = generateSecretKeyFromPassword(password, encryptedBundle.getSalt());

        // Decrypt message
        return decrypt(encryptedBundle.getEncryptedMessage(), secretKey, encryptedBundle.getInitializationVector());
    }
}