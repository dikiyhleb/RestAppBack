package dev.barapp.service;

import dev.barapp.DTOs.manager.ManagerCreateFoodDTO;
import dev.barapp.DTOs.manager.ManagerMenuDTO;
import dev.barapp.DTOs.user.UserMenuDTO;
import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.ImageEntity;
import dev.barapp.entities.MenuEntity;
import dev.barapp.mappers.MenuMapper;
import dev.barapp.repositories.FoodRepository;
import dev.barapp.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;
    private final MenuMapper menuMapper;

    public void createFood(FoodEntity food, long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        for(ImageEntity i : food.getImg()){
            i.setFood(food);
        }

        food.setMenu(menu);

        menu.addFood(food);

        menuRepository.save(menu);
    }

    public ManagerMenuDTO createFood(ManagerCreateFoodDTO food, long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        FoodEntity newFood = FoodEntity.builder()
                .name(food.getName())
                .img(food.getImg())
                .price(food.getPrice())
                .description(food.getDescription())
                .menu(menu)
                .build();

        for (ImageEntity i : newFood.getImg()){
            i.setFood(newFood);
        }

        menu.addFood(newFood);

        return menuMapper.toManagerMenuDTO(menuRepository.save(menu));
    }

    public void deleteFood(long foodId) {
        foodRepository.deleteById(foodId);
    }

    public UserMenuDTO getUserMenuByRestaurantId(long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return menuMapper.toUserMenuDTO(menu);
    }

    public ManagerMenuDTO getManagerMenuByRestaurantId(long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return menuMapper.toManagerMenuDTO(menu);
    }
}
