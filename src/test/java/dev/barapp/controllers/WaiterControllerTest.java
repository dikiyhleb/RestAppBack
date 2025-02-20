package dev.barapp.controllers;

import dev.barapp.controllers.roles.WithMockWaiter;
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
class WaiterControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void notLoggedInCantViewPrivateEndpoint() throws Exception {
        api.perform(get("/api/waiter/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockWaiter
    void loggedInWaiterCanViewPrivateEndpoint() throws Exception {
        api.perform(get("/api/waiter/test"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockWaiter
    void waiterCantViewAdminPrivateEndpoint() throws Exception {
        api.perform(get("/api/admin/test"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockWaiter
    void waiterCannotViewUserPrivateEndpoint() throws Exception {
        api.perform(get("/api/user/test"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockWaiter
    void waiterCannotViewManagerPrivateEndpoint() throws Exception {
        api.perform(get("/api/manager/test"))
                .andExpect(status().isForbidden());
    }
}