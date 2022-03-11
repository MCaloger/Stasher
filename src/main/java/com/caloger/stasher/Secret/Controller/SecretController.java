package com.caloger.stasher.Secret.Controller;

import com.caloger.stasher.Secret.Model.SecretModel;
import com.caloger.stasher.Secret.Service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/new")
    public String createSecret(@RequestBody SecretModel secret) {
        return "";
    }
}
