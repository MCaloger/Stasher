package com.caloger.stasher.Secured.Controller;

import com.caloger.stasher.Secured.Model.SecuredModel;
import com.caloger.stasher.Secured.Service.SecuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secured/")
public class SecuredController {

    SecuredService securedService;

    @Autowired
    public SecuredController(SecuredService securedService) {
        this.securedService = securedService;
    }

    @PostMapping("/code/{code}")
    public ResponseEntity<SecuredModel> getSecuredByCode(@PathVariable("code") String code, @RequestBody String password) {

        return ResponseEntity.ok().body(null);
    }
}
