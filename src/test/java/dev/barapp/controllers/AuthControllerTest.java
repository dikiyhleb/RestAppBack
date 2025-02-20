package dev.barapp.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @Autowired
    private MockMvc api;


    @Test
    void anyoneCanViewLogin() throws Exception {
        api.perform(post("/api/auth/login"))
                .andExpect(status().isOk());
    }

    @Test
    void anyoneCanViewRegister() throws Exception {
        api.perform(post("/api/auth/register"))
                .andExpect(status().isOk());
    }
}