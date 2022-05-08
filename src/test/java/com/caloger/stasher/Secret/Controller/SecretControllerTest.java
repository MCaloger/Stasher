package com.caloger.stasher.Secret.Controller;

import com.caloger.stasher.Secret.Model.Create.SecretCreationRequestModel;
import com.caloger.stasher.Secret.Model.Read.SecretReadRequestModel;
import com.caloger.stasher.Secret.Service.SecretService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecretControllerTest {

    private MockMvc mockMvc;
    private SecretService secretService;
    private ObjectMapper objectMapper;

    @Value("${domain}")
    private String domain;

    @Autowired
    public SecretControllerTest(MockMvc mockMvc, SecretService secretService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.secretService = secretService;
        this.objectMapper = objectMapper;
    }


    @Test
    void createSecret() throws Exception {

        String message = "My message.";

        SecretCreationRequestModel secretCreationRequestModel = new SecretCreationRequestModel(message, 1, 0);

        MvcResult createResult = mockMvc.perform(post("/api/v1/secret/create/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secretCreationRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("code").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("code").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").isString())
                .andReturn();

        String code = JsonPath.read(createResult.getResponse().getContentAsString(), "code");
        String uri = JsonPath.read(createResult.getResponse().getContentAsString(), "uri");

        then(code).isNotEmpty();
        then(uri).isEqualTo(domain + "/secret/code/" + code);

        // read created

        //build request
        SecretReadRequestModel secretReadRequestModel = new SecretReadRequestModel(code);

        // perform read call
        MvcResult readResult = mockMvc.perform(get("/api/v1/secret/code/" + code).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(secretReadRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("message").isString())
                .andReturn();

        // fetch message from json
        String readMessage = JsonPath.read(readResult.getResponse().getContentAsString(), "message");

        // check equality with message
        then(readMessage).isEqualTo(message);

        // ensure item is deleted
        then(secretService.checkIfSecretExists(code)).isEqualTo(false);
    }
}