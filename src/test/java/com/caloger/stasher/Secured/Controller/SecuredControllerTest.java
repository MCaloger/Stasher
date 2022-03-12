package com.caloger.stasher.Secured.Controller;

import com.caloger.stasher.Secured.Model.SecuredModel;
import com.caloger.stasher.Secured.Model.Create.SecuredCreationRequestModel;
import com.caloger.stasher.Secured.Service.SecuredService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecuredControllerTest {

    private MockMvc mockMvc;
    private SecuredService securedService;
    private ObjectMapper objectMapper;

    @Autowired
    public SecuredControllerTest(MockMvc mockMvc, SecuredService securedService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.securedService = securedService;
        this.objectMapper = objectMapper;
    }

    @Test
    void getSecuredByCode() {
    }

    @Test
    void createSecured() throws Exception {
        // given
        SecuredModel securedModel = securedService.createSecured(new SecuredCreationRequestModel("my message",
                "my password"));
        // when

        // then
        mockMvc.perform(post("/api/secured/create/").content(objectMapper.writeValueAsString(securedModel)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}