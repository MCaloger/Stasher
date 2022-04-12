package com.caloger.stasher.Secret.Service;

import com.caloger.stasher.Secret.Model.Create.SecretCreationRequestModel;
import com.caloger.stasher.Secret.Model.SecretModel;

import java.util.List;

public interface SecretService {
    public SecretModel createSecret(SecretCreationRequestModel secretCreationRequestModel);
    public String readSecretByCode(String code) throws Exception;
    void deleteSecret(Long id);

    boolean checkIfSecretExists(String code);
    List<SecretModel> deleteExpired();
}
