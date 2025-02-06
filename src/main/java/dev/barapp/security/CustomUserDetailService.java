package dev.barapp.security;


import dev.barapp.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final CredentialService credentialService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var credential = credentialService.findByEmail(username).orElseThrow();
        return CredentialPrincipal.builder()
                .credentialId(credential.getId())
                .role(credential.getRole())
                .email(credential.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(credential.getRole().name())))
                .password(credential.getPassword())
                .build();
    }
}
