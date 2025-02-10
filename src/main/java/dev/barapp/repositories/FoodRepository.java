package dev.barapp.repositories;

import dev.barapp.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

}
