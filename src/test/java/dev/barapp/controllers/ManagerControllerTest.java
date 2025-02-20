package dev.barapp.controllers;

import dev.barapp.controllers.roles.WithMockManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void notLoggedInCannotViewPrivateEndpoint() throws Exception {
        mockMvc.perform(get("/api/manager/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockManager
    void loggedInCanViewPrivateEndpoint() throws Exception {
        mockMvc.perform(get("/api/manager/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    @WithMockManager
    void managerCanNotViewOtherPrivateEndpoint() throws Exception {
        mockMvc.perform(get("/api/waiter/test"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/user/test"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/admin/test"))
                .andExpect(status().isForbidden());
    }
}