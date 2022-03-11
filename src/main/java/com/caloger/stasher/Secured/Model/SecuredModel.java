package com.caloger.stasher.Secured.Model;

import com.caloger.stasher.Encryption.Model.EncryptedBundle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SecuredModel {
    @Id
    @GeneratedValue
    Long id;

    EncryptedBundle encryptedBundle;

    public SecuredModel() {
    }

    public SecuredModel(Long id, EncryptedBundle encryptedBundle) {
        this.id = id;
        this.encryptedBundle = encryptedBundle;
    }

    public SecuredModel(EncryptedBundle encryptedBundle) {
        this.encryptedBundle = encryptedBundle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EncryptedBundle getEncryptedBundle() {
        return encryptedBundle;
    }

    public void setEncryptedBundle(EncryptedBundle encryptedBundle) {
        this.encryptedBundle = encryptedBundle;
    }
}
