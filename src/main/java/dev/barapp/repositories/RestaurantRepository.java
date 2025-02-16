package dev.barapp.repositories;


import dev.barapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    public Optional<RestaurantEntity> findRestaurantEntityByManagerId(Long managerId);

    public Optional<RestaurantEntity> findRestaurantEntityByWaitersId(Long waitersId);
}
