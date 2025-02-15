package dev.barapp.repositories;


import dev.barapp.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    public RestaurantEntity findRestaurantEntityByManagerId(Long managerId);
}
