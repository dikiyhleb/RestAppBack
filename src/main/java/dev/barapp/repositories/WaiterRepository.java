package dev.barapp.repositories;

import dev.barapp.entities.WaiterEntity;
import org.springframework.data.repository.CrudRepository;

public interface WaiterRepository extends CrudRepository<WaiterEntity, Long> {
    WaiterEntity findWaiterEntityByCredentialEntity_Id(long id);
}
