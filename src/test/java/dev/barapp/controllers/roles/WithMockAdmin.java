package dev.barapp.controllers.roles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(authorities = "ADMIN")
public @interface WithMockAdmin {
}
