package com.caloger.stasher.Secrets;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/secret")
public class SecretController {

    @GetMapping("/:code")
    public SecretModel getSecretByCode(@Param("code") String code) {
        return new SecretModel();
    }

    @PostMapping("/new")
    public String createSecret(@RequestBody SecretModel secret) {
        return "";
    }
}
