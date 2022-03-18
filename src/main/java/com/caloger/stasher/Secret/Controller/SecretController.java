package com.caloger.stasher.Secret.Controller;

import com.caloger.stasher.Core.Domain.Service.DomainService;
import com.caloger.stasher.Secret.Model.Create.SecretCreationRequestModel;
import com.caloger.stasher.Secret.Model.Create.SecretCreationResponseModel;
import com.caloger.stasher.Secret.Model.Read.SecretReadResponseModel;
import com.caloger.stasher.Secret.Model.SecretModel;
import com.caloger.stasher.Secret.Service.SecretService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/secret")
public class SecretController {

    SecretService secretService;
    DomainService domainService;

    @Autowired
    public SecretController(SecretService secretService, DomainService domainService) {
        this.secretService = secretService;
        this.domainService = domainService;
    }

    @GetMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public ResponseEntity<SecretReadResponseModel> getSecretByCode(
            @NotNull @PathVariable("code") String code) {

        SecretReadResponseModel secretReadResponseModel = new SecretReadResponseModel();

        try {
            secretReadResponseModel.setMessage(
                    secretService.readSecretByCode(code));
            return new ResponseEntity(secretReadResponseModel, HttpStatus.OK);
        } catch(Exception exception) {
            secretReadResponseModel.setMessage(exception.getMessage());
            return new ResponseEntity(secretReadResponseModel, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public ResponseEntity<SecretCreationResponseModel> createSecret(
            @Valid @RequestBody SecretCreationRequestModel secretCreationRequestModel) {

        SecretCreationResponseModel secretCreationResponseModel = new SecretCreationResponseModel();

        try {
            SecretModel secretModel = secretService.createSecret(secretCreationRequestModel.getMessage());
            secretCreationResponseModel = new SecretCreationResponseModel(domainService.getDomain(),
                    secretModel.getCode());

            return new ResponseEntity(secretCreationResponseModel, HttpStatus.CREATED);

        } catch(Exception exception) {
            return new ResponseEntity(secretCreationResponseModel, HttpStatus.BAD_REQUEST);
        }

    }
}
