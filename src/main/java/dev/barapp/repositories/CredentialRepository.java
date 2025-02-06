package dev.barapp.repositories;

import dev.barapp.entities.CredentialEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<CredentialEntity, Long> {
    Optional<CredentialEntity> findCredentialEntityByEmail(String email);
}
