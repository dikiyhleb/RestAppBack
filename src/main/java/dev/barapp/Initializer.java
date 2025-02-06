package dev.barapp;

import dev.barapp.entities.*;
import dev.barapp.repositories.CredentialRepository;
import dev.barapp.repositories.ManagerRepository;
import dev.barapp.repositories.UserRepository;
import dev.barapp.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WaiterRepository waiterRepository;
    @Autowired
    private ManagerRepository managerRepository;

    public void initialize() {
        userRepository.save(UserEntity.builder()
                .name("Ivan Ivanov")
                .credentialEntity(credentialRepository.save(CredentialEntity.builder()
                        .role(Role.USER)
                        .email("ivanivanov@example.com")
                        .password("1234")
                        .build()))
                .build());
        waiterRepository.save(WaiterEntity.builder()
                .name("Oksana Strukova")
                .credentialEntity(credentialRepository.save(CredentialEntity.builder()
                        .role(Role.WAITER)
                        .email("waiter@example.com")
                        .password("test")
                        .build()))
                .build());
        managerRepository.save(ManagerEntity.builder()
                .name("Petr Petrov")
                .credentialEntity(credentialRepository.save(CredentialEntity.builder()
                        .role(Role.MANAGER)
                        .email("manager@example.com")
                        .password("1234")
                        .build()))
                .build());
    }
}
