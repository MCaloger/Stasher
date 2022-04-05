package com.caloger.stasher.Secured.Service;

import com.caloger.stasher.Core.Code.Service.CodeService;
import com.caloger.stasher.Encryption.Model.EncryptedPropertiesModel;
import com.caloger.stasher.Encryption.Service.EncryptionService;
import com.caloger.stasher.Secured.Model.SecuredModel;
import com.caloger.stasher.Secured.Model.Create.SecuredCreationRequestModel;
import com.caloger.stasher.Secured.Repository.SecuredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

@Service
public class SecuredServiceImpl implements SecuredService{

    EncryptionService encryptionService;
    CodeService codeService;
    SecuredRepository securedRepository;

    @Autowired
    public SecuredServiceImpl(EncryptionService encryptionService, CodeService codeService,
                              SecuredRepository securedRepository) {
        this.encryptionService = encryptionService;
        this.codeService = codeService;
        this.securedRepository = securedRepository;
    }

    /**
     * Create secured message
     * @param securedCreationRequestModel
     * @return saved model
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public SecuredModel createSecured(SecuredCreationRequestModel securedCreationRequestModel)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        // encrypt message
        EncryptedPropertiesModel encryptedProperties = encryptionService.encryptMessage(securedCreationRequestModel.getMessage(),
                securedCreationRequestModel.getPassword());

        // generate code
        String code = codeService.generateCode();

        // generate expiry
        LocalDateTime expiry = LocalDateTime.now().plusHours(1);

        // build secured model
        SecuredModel securedModel = new SecuredModel(code, encryptedProperties.getEncryptedMessage(),
                encryptedProperties.getInitializationVectorSeed(), encryptedProperties.getSalt(), expiry);

        // save secured model
        return securedRepository.save(securedModel);
    }

    /**
     * Read a secured message
     * @param code
     * @param password
     * @return Message if password is correct and message is valid
     * @throws Exception
     */
    public String readSecuredByCodeWithPassword(String code, String password) throws Exception {
        SecuredModel securedModel = null;
        String message = "";
        try {
            securedModel = securedRepository.findByCode(code);
            if(securedModel != null && securedModel.getExpiry().isAfter(LocalDateTime.now())) {
                message = encryptionService.decryptMessage(password, securedModel);
            } else {
                message = "Message is missing, password was incorrect, or is no longer available.";
            }
            return message;

        } catch(NullPointerException exception) {
            throw new Exception("Message is missing, password was incorrect, or is no longer available.");
        } catch(Exception exception) {
            throw new Exception("Message is missing, password was incorrect, or is no longer available.");
        } finally {
            if(securedModel != null && securedModel.getId() != null) {
                deleteSecured(securedModel.getId());
            }
        }
    }

    /**
     * Check if secured message exists by ID
     * @param id
     * @return
     */
    public boolean checkIfSecuredExists(Long id) {
        return securedRepository.existsById(id);
    }

    /**
     * Check if secured message exosts by code
     * @param code
     * @return
     */
    public boolean checkIfSecuredExists(String code) {
        return securedRepository.existsByCode(code);
    }

    /**
     * delete secured code by id
     * @param id
     */
    public void deleteSecured(Long id) {
        if(securedRepository.existsById(id)){
            securedRepository.deleteById(id);
        }
    }

}
