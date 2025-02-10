package dev.barapp.repositories;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
    MenuEntity findMenuEntityByRestaurantId(long restaurantId);
}
