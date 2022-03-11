package com.caloger.stasher.Secret.Service;

import com.caloger.stasher.Secret.Repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretServiceImpl implements SecretService {

    private SecretRepository secretRepository;

    @Autowired
    public SecretServiceImpl(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }
}
