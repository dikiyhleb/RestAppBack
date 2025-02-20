package dev.barapp.controllers.roles;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(authorities = "WAITER")
public @interface WithMockWaiter {
}
