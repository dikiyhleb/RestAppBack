package dev.barapp.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CredentialPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final CredentialPrincipal principal;

    public CredentialPrincipalAuthenticationToken(CredentialPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public CredentialPrincipal getPrincipal() {
        return principal;
    }
}
