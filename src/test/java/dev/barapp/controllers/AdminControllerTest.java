package dev.barapp.controllers;

import dev.barapp.controllers.roles.WithMockAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void notLoggedInCannotViewPrivateEndpoints() throws Exception {
        mockMvc.perform(get("/api/admin/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockAdmin
    void loggedInCanViewPrivateEndpoints() throws Exception {
        mockMvc.perform(get("/api/admin/test"))
                .andExpect(status().isOk())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockAdmin
    void managerCannotViewOtherPrivateEndpoints() throws Exception {
        mockMvc.perform(get("/api/user/test"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/waiter/test"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/manager/test"))
                .andExpect(status().isForbidden());
    }
}