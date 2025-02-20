package dev.barapp.repositories;

import dev.barapp.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntityByCredentialEntity_Id(long id);

    UserEntity findUserEntityByCredentialEntityEmail(String email);
}
