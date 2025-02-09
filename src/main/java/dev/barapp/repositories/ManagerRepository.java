package dev.barapp.repositories;

import dev.barapp.entities.ManagerEntity;
import dev.barapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ManagerRepository extends CrudRepository<ManagerEntity, Long> {
    ManagerEntity findManagerEntityByCredentialEntity_Id(long id);

    @Query("SELECT m.restaurant FROM ManagerEntity m WHERE m.id = :managerId")
    RestaurantEntity findRestaurantEntityByManagerEntity_Id(@Param(value = "managerId") long id);
}
