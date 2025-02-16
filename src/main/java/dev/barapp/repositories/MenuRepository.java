package dev.barapp.repositories;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    Optional<MenuEntity> findMenuEntityByRestaurantId(long restaurantId);
}
