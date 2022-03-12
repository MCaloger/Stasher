package com.caloger.stasher.Secret.Controller;

import com.caloger.stasher.Secret.Model.SecretModel;
import com.caloger.stasher.Secret.Service.SecretService;
import com.caloger.stasher.Secured.Model.Read.SecuredReadResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/secret")
public class SecretController {

    SecretService secretService;

    @Autowired
    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @GetMapping("/code/{code}")
    public SecretModel getSecretByCode(@PathVariable("code") String code) {
        return new SecretModel();
    }

//    @PostMapping("/new")
//    public ResponseEntity<SecretModel> createSecret(@Valid @RequestBody SecretModel secret) {
//
//    }
}
