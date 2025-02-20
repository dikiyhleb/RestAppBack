package dev.barapp.controllers.roles;

import dev.barapp.security.CredentialPrincipal;
import dev.barapp.security.CredentialPrincipalAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        var authorities = Arrays.stream(annotation.authorities())
                .map(SimpleGrantedAuthority::new)
                .toList();

        var principal = CredentialPrincipal.builder()
                .credentialId(annotation.userId())
                .email("fake@email.com")
                .authorities(authorities)
                .build();

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new CredentialPrincipalAuthenticationToken(principal));
        return context;
    }
}
