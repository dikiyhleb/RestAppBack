package dev.barapp.service;

import dev.barapp.entities.UserEntity;
import dev.barapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity findUserByEmail(String email) {
        return userRepository.findUserEntityByCredentialEntityEmail(email);
    }
}
