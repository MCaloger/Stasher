package com.caloger.stasher.Secured.Controller;

import com.caloger.stasher.Secured.Model.Read.SecuredReadRequestModel;
import com.caloger.stasher.Secured.Model.SecuredModel;
import com.caloger.stasher.Secured.Model.Create.SecuredCreationRequestModel;
import com.caloger.stasher.Secured.Service.SecuredService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecuredControllerTest {

    private MockMvc mockMvc;
    private SecuredService securedService;
    private ObjectMapper objectMapper;

    @Value("${domain}")
    private String domain;

    @Autowired
    public SecuredControllerTest(MockMvc mockMvc, SecuredService securedService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.securedService = securedService;
        this.objectMapper = objectMapper;
    }

    @Test
    void getSecuredByCode() {
    }

    @DisplayName("Secured Creation")
    @Test
    void createSecured() throws Exception {
        // given
        SecuredCreationRequestModel securedCreationRequestModel = new SecuredCreationRequestModel("My message", "my password");

        // when

        // then
        MvcResult mvcResult = mockMvc.perform(post("/api/secured/create/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(securedCreationRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("code").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("code").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").isString())
                .andReturn();

        String code = JsonPath.read(mvcResult.getResponse().getContentAsString(), "code");
        String uri = JsonPath.read(mvcResult.getResponse().getContentAsString(), "uri");

        then(code).isNotEmpty();
        then(uri).isEqualTo(domain + "/secured/code/" + code);
    }

    @DisplayName("Create secured message and then read it back, then ensure item was deleted.")
    @Test
    void createSecuredAndRead() throws Exception {
        String message = "My message.";
        String password = "mypassword";

        SecuredCreationRequestModel securedCreationRequestModel = new SecuredCreationRequestModel(message, password);

        MvcResult createResult = mockMvc.perform(post("/api/secured/create/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(securedCreationRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("code").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("code").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").isString())
                .andReturn();

        String code = JsonPath.read(createResult.getResponse().getContentAsString(), "code");
        String uri = JsonPath.read(createResult.getResponse().getContentAsString(), "uri");

        // ensure that uri is equal to full url
        then(uri).isEqualTo(domain + "/secured/code/" + code);

        // read created

        //build request
        SecuredReadRequestModel securedReadRequestModel = new SecuredReadRequestModel(password);

        // perform read call
        MvcResult readResult = mockMvc.perform(post("/api/secured/code/" + code).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(securedReadRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("message").isString())
                .andReturn();

        // fetch message from json
        String readMessage = JsonPath.read(readResult.getResponse().getContentAsString(), "message");

        // check equality with message
        then(readMessage).isEqualTo(message);

        // ensure item is deleted
        then(securedService.checkIfSecuredExists(code)).isEqualTo(false);
    }

    @DisplayName("Create secured message and then read with incorrect password, then ensure item was deleted.")
    @Test
    void createSecuredAndReadWithIncorrectPassword() throws Exception {

        String message = "My message.";
        String correctPassword = "mypassword";
        String wrongPassword = "pass";

        SecuredCreationRequestModel securedCreationRequestModel = new SecuredCreationRequestModel(message, correctPassword);

        MvcResult createResult = mockMvc.perform(post("/api/secured/create/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(securedCreationRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("code").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("code").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("uri").isString())
                .andReturn();

        String code = JsonPath.read(createResult.getResponse().getContentAsString(), "code");
        String uri = JsonPath.read(createResult.getResponse().getContentAsString(), "uri");

        then(uri).isEqualTo(domain + "/secured/code/" + code);

        // read created

        SecuredReadRequestModel securedReadRequestModel = new SecuredReadRequestModel(wrongPassword);

        MvcResult readResult = mockMvc.perform(post("/api/secured/code/" + code).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(securedReadRequestModel)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("message").isString())
                .andReturn();

        String readMessage = JsonPath.read(readResult.getResponse().getContentAsString(), "message");

        then(readMessage).isNotEqualTo(message);

        then(securedService.checkIfSecuredExists(code)).isEqualTo(false);
    }
}