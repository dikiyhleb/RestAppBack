package dev.barapp.service;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.MenuEntity;
import dev.barapp.repositories.FoodRepository;
import dev.barapp.repositories.MenuRepository;
import dev.barapp.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    public MenuEntity createFood(FoodEntity food, long restId) {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId);

        menu.addFood(food);

        return menuRepository.save(menu);
    }

    public List<FoodEntity> getAllFood(long restId) {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId);

        return menu.getFoods();
    }
}
