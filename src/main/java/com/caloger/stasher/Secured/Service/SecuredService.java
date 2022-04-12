package com.caloger.stasher.Secured.Service;

import com.caloger.stasher.Secret.Model.SecretModel;
import com.caloger.stasher.Secured.Model.SecuredModel;
import com.caloger.stasher.Secured.Model.Create.SecuredCreationRequestModel;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface SecuredService {
    public SecuredModel createSecured(SecuredCreationRequestModel securedCreationRequestModel)
            throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException;

    public String readSecuredByCodeWithPassword(String code, String password) throws Exception;

    public void deleteSecured(Long id);

    public boolean checkIfSecuredExists(Long id);

    public boolean checkIfSecuredExists(String code);

    List<SecuredModel> deleteExpired();
}
