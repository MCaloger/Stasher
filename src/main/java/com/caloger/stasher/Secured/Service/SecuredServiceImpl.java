package com.caloger.stasher.Secured.Service;

import com.caloger.stasher.Core.Code.Service.CodeService;
import com.caloger.stasher.Encryption.Model.EncryptedProperties;
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
import java.time.LocalTime;

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

    public SecuredModel createSecured(SecuredCreationRequestModel securedCreationRequestModel)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {

        // encrypt message
        EncryptedProperties encryptedProperties = encryptionService.encryptMessage(securedCreationRequestModel.getMessage(),
                securedCreationRequestModel.getPassword());

        // generate code
        String code = codeService.generateCode();

        // generate expiry
        LocalTime expiry = LocalTime.now().plusHours(1);

        // build secured model
        SecuredModel securedModel = new SecuredModel(code, encryptedProperties.getEncryptedMessage(),
                encryptedProperties.getInitializationVectorSeed(), encryptedProperties.getSalt(), expiry);

        // save secured model
        return securedRepository.save(securedModel);
    }

    public String readSecuredByCodeWithPassword(String code, String password) throws Exception {
        SecuredModel securedModel = null;
        String message = "";
        try {
            securedModel = securedRepository.findByCode(code);
            if(securedModel != null) {
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

    public boolean checkIfSecuredExists(Long id) {
        return securedRepository.existsById(id);
    }

    public boolean checkIfSecuredExists(String code) {
        return securedRepository.existsByCode(code);
    }

    public void deleteSecured(Long id) {
        if(securedRepository.existsById(id)){
            securedRepository.deleteById(id);
        }
    }

}
