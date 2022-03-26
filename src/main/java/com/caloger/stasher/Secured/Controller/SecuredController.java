package com.caloger.stasher.Secured.Controller;

import com.caloger.stasher.Core.Domain.Service.DomainService;
import com.caloger.stasher.Secured.Model.Read.SecuredReadRequestModel;
import com.caloger.stasher.Secured.Model.Read.SecuredReadResponseModel;
import com.caloger.stasher.Secured.Model.SecuredModel;
import com.caloger.stasher.Secured.Model.Create.SecuredCreationRequestModel;
import com.caloger.stasher.Secured.Model.Create.SecuredCreationResponseModel;
import com.caloger.stasher.Secured.Service.SecuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/secured/")
public class SecuredController {

    private final SecuredService securedService;
    private final DomainService domainService;

    @Autowired
    public SecuredController(SecuredService securedService, DomainService domainService) {
        this.securedService = securedService;
        this.domainService = domainService;
    }

    /**
     * Handle reading of secured message
     * @param code
     * @param securedReadRequestModel
     * @return
     */
    @PostMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SecuredReadResponseModel> getSecuredByCode(
            @PathVariable("code") String code,
            @NotNull @RequestBody SecuredReadRequestModel securedReadRequestModel) {

        SecuredReadResponseModel securedReadResponseModel = new SecuredReadResponseModel();

        try {
            securedReadResponseModel.setMessage(
                    securedService.readSecuredByCodeWithPassword(code, securedReadRequestModel.getPassword()));
            return new ResponseEntity(securedReadResponseModel, HttpStatus.OK);
        } catch(Exception exception) {
            securedReadResponseModel.setMessage(exception.getMessage());
            return new ResponseEntity(securedReadResponseModel, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handle creation of secured message
     * @param securedModelRequest
     * @return
     */
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SecuredCreationResponseModel> createSecured (
            @Valid @NotNull @RequestBody SecuredCreationRequestModel securedModelRequest) {

        try {
            SecuredModel securedModel = securedService.createSecured(securedModelRequest);
            SecuredCreationResponseModel securedCreationResponseModel = new SecuredCreationResponseModel(domainService.getDomain(), securedModel.getCode());
            return new ResponseEntity(securedCreationResponseModel, HttpStatus.CREATED);
        } catch(Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error");
        }
    }
}
