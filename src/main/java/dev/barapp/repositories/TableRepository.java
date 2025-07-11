package dev.barapp.repositories;

import dev.barapp.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
    Optional<TableEntity[]> findTableEntitiesByRestaurantId(long restaurantId);
}
