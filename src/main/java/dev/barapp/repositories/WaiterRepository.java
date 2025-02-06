package dev.barapp.repositories;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.WaiterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WaiterRepository extends CrudRepository<WaiterEntity, Long> {
    WaiterEntity findWaiterEntityByCredentialEntity_Id(long id);

    @Query("SELECT w.restaurant FROM WaiterEntity w WHERE w.id = :waiterId")
    Optional<RestaurantEntity> findRestaurantEntityByWaiterEntity_Id(@Param("waiterId") long waiterId);
}
