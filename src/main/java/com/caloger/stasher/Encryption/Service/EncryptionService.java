package com.caloger.stasher.Encryption.Service;

import com.caloger.stasher.Encryption.Model.EncryptedPropertiesModel;
import com.caloger.stasher.Secured.Model.SecuredModel;
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

    final private int KEY_LENGTH = 256;
    final private int ITERATION_COUNT = 65536;
    final private String ALGORITHM = "AES/CBC/PKCS5Padding";
    final private int INITIALIZATION_VECTOR_LENGTH = 16;

    /**
     * @return Key
     * @throws NoSuchAlgorithmException
     */
    private SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(this.KEY_LENGTH);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    /**
     * @param password
     * @param salt
     * @return Secret provided password and salt
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private SecretKey generateSecretKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), this.ITERATION_COUNT, this.KEY_LENGTH);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    /**
     * @return Initialization vector seed
     */
    private byte[] generateInitializationVectorSeed() {
        byte[] initializationVectorSeed = new byte[INITIALIZATION_VECTOR_LENGTH];
        new SecureRandom().nextBytes(initializationVectorSeed);
        return initializationVectorSeed;
    }

    private IvParameterSpec generateInitializationVectorFromSeed(byte[] seed) {
        return new IvParameterSpec(seed);
    }

    /**
     * @return Salt
     */
    private String generateSalt() {
        byte[] salt = new byte[KEY_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt.toString();
    }

    /**
     * @param input
     * @param key
     * @param initializationVectorSeed
     * @return Encrypted string
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private String encrypt(String input, SecretKey key,
                                 byte[] initializationVectorSeed) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.ALGORITHM);
        IvParameterSpec initializationVector = generateInitializationVectorFromSeed(initializationVectorSeed);
        cipher.init(Cipher.ENCRYPT_MODE, key, initializationVector);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    /**
     * @param cipherText
     * @param key
     * @param initializationVectorSeed
     * @return Message
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private String decrypt(String cipherText, SecretKey key,
                                 byte[] initializationVectorSeed) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(this.ALGORITHM);
        IvParameterSpec initializationVector = generateInitializationVectorFromSeed(initializationVectorSeed);
        cipher.init(Cipher.DECRYPT_MODE, key, initializationVector);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }

    /**
     * Encrypts provided message wsing provided password in key.
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
    public EncryptedPropertiesModel encryptMessage(String message, String unencryptedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Generate salt
        String salt = generateSalt();

        // Generate key combining password and generated salt
        SecretKey secretKey = generateSecretKeyFromPassword(unencryptedPassword, salt);

        // Generate an initialization vector
        byte[] initializationVectorSeed = generateInitializationVectorSeed();

        // Encrypt the message
        String encryptedMessage = encrypt(message, secretKey, initializationVectorSeed);

        // Return the encrypted bundle
        return new EncryptedPropertiesModel(encryptedMessage, secretKey, initializationVectorSeed, salt);
    }

    /**
     * Decrypt with provided password and EncryptedBundle.
     * @param password
     * @param encryptedProperties
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public String decryptMessage(String password, EncryptedPropertiesModel encryptedProperties) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        // Build key from provided password and saved salt
        SecretKey secretKey = generateSecretKeyFromPassword(password, encryptedProperties.getSalt());

        // Decrypt message
        return decrypt(encryptedProperties.getEncryptedMessage(), secretKey, encryptedProperties.getInitializationVectorSeed());
    }

    /**
     * @param password
     * @param securedModel
     * @return Decrypted message
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public String decryptMessage(String password, SecuredModel securedModel) throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException,
            InvalidKeyException, InvalidKeySpecException {

        EncryptedPropertiesModel encryptedProperties = new EncryptedPropertiesModel(securedModel.
                getEncryptedProperties().getEncryptedMessage(),
                securedModel.getEncryptedProperties().getInitializationVectorSeed(),
                securedModel.getEncryptedProperties().getSalt());

        // Build key from provided password and saved salt
        SecretKey secretKey = generateSecretKeyFromPassword(password, encryptedProperties.getSalt());

        // Decrypt message
        return decrypt(encryptedProperties.getEncryptedMessage(), secretKey, encryptedProperties.getInitializationVectorSeed());
    }
}