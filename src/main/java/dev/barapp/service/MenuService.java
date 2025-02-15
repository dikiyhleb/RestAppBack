package dev.barapp.service;

import dev.barapp.DTOs.manager.ManagerMenuDTO;
import dev.barapp.DTOs.user.UserMenuDTO;
import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.ImageEntity;
import dev.barapp.entities.MenuEntity;
import dev.barapp.mappers.MenuMapper;
import dev.barapp.repositories.FoodRepository;
import dev.barapp.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;
    @Autowired
    private FoodRepository foodRepository;

    private MenuMapper menuMapper;

    public MenuEntity createFood(FoodEntity food, long restId) {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId);

        for(ImageEntity i : food.getImg()){
            i.setFood(food);
        }

        food.setMenu(menu);

        menu.addFood(food);

        return menuRepository.save(menu);
    }

    public void deleteFood(long foodId) {
        foodRepository.deleteById(foodId);
    }

    public MenuEntity getMenuByRestaurantId(long restId) {
        return menuRepository.findMenuEntityByRestaurantId(restId);
    }
}
