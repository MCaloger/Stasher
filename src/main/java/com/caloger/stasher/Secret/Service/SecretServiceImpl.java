package com.caloger.stasher.Secret.Service;

import com.caloger.stasher.Core.Code.Service.CodeService;
import com.caloger.stasher.Secret.Model.SecretModel;
import com.caloger.stasher.Secret.Repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class SecretServiceImpl implements SecretService {

    private SecretRepository secretRepository;
    private CodeService codeService;

    @Autowired
    public SecretServiceImpl(SecretRepository secretRepository, CodeService codeService) {
        this.secretRepository = secretRepository;
        this.codeService = codeService;
    }

    public SecretModel createSecret(String message) {
        // generate code
        String code = codeService.generateCode();

        // generate expiry
        LocalDateTime expiry = LocalDateTime.now().plusHours(1);

        SecretModel secretModel = new SecretModel(code, message, expiry);

        return secretRepository.save(secretModel);
    }

    public String readSecretByCode(String code) throws Exception {
        SecretModel secretModel = null;
        String message = "";

        try {
            secretModel = secretRepository.findByCode(code);

            if(secretModel != null && secretModel.getExpiry().isAfter(LocalDateTime.now())) {
                message = secretModel.getMessage();
            } else {
                message = "Message is missing or is no longer available.";
            }
            return message;
        } catch(NullPointerException exception) {
            throw new Exception("Message is missing, password was incorrect, or is no longer available.");
        } catch(Exception exception) {
            throw new Exception("Message is missing, password was incorrect, or is no longer available.");
        } finally {
            if(secretModel != null && secretModel.getId() != null) {
                deleteSecret(secretModel.getId());
            }
        }

    }

    public void deleteSecret(Long id) {
        if(secretRepository.existsById(id)) {
            secretRepository.deleteById(id);
        }
    }

    @Override
    public boolean checkIfSecretExists(String code) {
        return secretRepository.existsByCode(code);
    }
}
