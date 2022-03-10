package com.caloger.stasher.Secrets;

import org.springframework.beans.factory.annotation.Autowired;

public class SecretService {

    private SecretRepository secretRepository;

    @Autowired
    public SecretService(SecretRepository secretRepository) {
        this.secretRepository = secretRepository;
    }
}
