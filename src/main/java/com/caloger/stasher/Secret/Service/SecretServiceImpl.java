package com.caloger.stasher.Secret.Service;

import com.caloger.stasher.Core.Code.Service.CodeService;
import com.caloger.stasher.Secret.Model.Create.SecretCreationRequestModel;
import com.caloger.stasher.Secret.Model.SecretModel;
import com.caloger.stasher.Secret.Repository.SecretRepository;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class SecretServiceImpl implements SecretService {

    private SecretRepository secretRepository;
    private CodeService codeService;

    @Autowired
    public SecretServiceImpl(SecretRepository secretRepository, CodeService codeService) {
        this.secretRepository = secretRepository;
        this.codeService = codeService;
    }

    /**
     * @param secretCreationRequestModel
     * @return SecretModel
     */
    public SecretModel createSecret(SecretCreationRequestModel secretCreationRequestModel) {
        // generate code
        String code = codeService.generateCode();

        // generate expiry
        LocalDateTime expiry = secretCreationRequestModel.getExpiry();

        SecretModel secretModel = new SecretModel(code, secretCreationRequestModel.getMessage(), expiry);

        return secretRepository.save(secretModel);
    }

    /**
     * @param code
     * @return Message
     * @throws Exception
     */
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

    /**
     * @param id
     */
    public void deleteSecret(Long id) {
        if(secretRepository.existsById(id)) {
            secretRepository.deleteById(id);
        }
    }

    /**
     * @param code
     * @return
     */
    @Override
    public boolean checkIfSecretExists(String code) {
        return secretRepository.existsByCode(code);
    }

    /**
     * Delete all expired secured secrets on a delay
     * @return
     */
    @Scheduled(fixedDelay = 60*1*1000)
    public List<SecretModel> deleteExpired() {

        List<SecretModel> secretModels = secretRepository.findAllWithExpiredTimeBefore(LocalDateTime.now());
        secretModels.forEach(secretModel -> secretRepository.deleteById(secretModel.getId()));
        return secretModels;
    }
}
