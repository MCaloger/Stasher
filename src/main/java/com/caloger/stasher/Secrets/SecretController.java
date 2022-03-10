package com.caloger.stasher.Secrets;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secret")
public class SecretController {

    @GetMapping("/code/{code}")
    public SecretModel getSecretByCode(@PathVariable("code") String code) {
        return new SecretModel();
    }

    @PostMapping("/new")
    public String createSecret(@RequestBody SecretModel secret) {
        return "";
    }
}
