package dev.barapp.repositories;

import dev.barapp.entities.ManagerEntity;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<ManagerEntity, Long> {
    ManagerEntity findManagerEntityByCredentialEntity_Id(long id);
}
