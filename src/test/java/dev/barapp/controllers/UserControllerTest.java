package dev.barapp.controllers;

import dev.barapp.controllers.roles.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void notLoggedInCantViewPrivateEndpoint() throws Exception {
        api.perform(get("/api/user/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedInCanViewPrivateUserEndpoint() throws Exception {
        api.perform(get("/api/user/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    @WithMockUser
    void loggedInCantViewPrivateAdminEndpoint() throws Exception {
        api.perform(get("/api/admin/test"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void loggedInCantViewPrivateWaiterEndpoint() throws Exception {
        api.perform(get("/api/waiter/test"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void loggedInCantViewPrivateManagerEndpoint() throws Exception {
        api.perform(get("/api/manager/test"))
                .andExpect(status().isForbidden());
    }
}