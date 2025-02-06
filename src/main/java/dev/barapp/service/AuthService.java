package dev.barapp.service;

import dev.barapp.entities.BaseUserEntity;
import dev.barapp.entities.Role;
import dev.barapp.model.LoginResponse;
import dev.barapp.repositories.ManagerRepository;
import dev.barapp.repositories.UserRepository;
import dev.barapp.repositories.WaiterRepository;
import dev.barapp.security.JwtIssuer;
import dev.barapp.security.CredentialPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final WaiterRepository waiterRepository;

    public LoginResponse attemptLogin(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (CredentialPrincipal)authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getCredentialId(), principal.getEmail(), roles);


        return LoginResponse.builder()
                .accessToken(token)
                .data(getAuthorizedUser(principal))
                .build();
    }

    private <T extends BaseUserEntity> T getAuthorizedUser(CredentialPrincipal principal) {
        return switch (principal.getRole()) {
            case USER -> (T) userRepository.findUserEntityByCredentialEntity_Id(principal.getCredentialId());
            case MANAGER -> (T) managerRepository.findManagerEntityByCredentialEntity_Id(principal.getCredentialId());
            case WAITER -> (T) waiterRepository.findWaiterEntityByCredentialEntity_Id(principal.getCredentialId());
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
}
