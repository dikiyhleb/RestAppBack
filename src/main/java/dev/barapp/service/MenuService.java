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
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    @Autowired
    private final MenuRepository menuRepository;
    @Autowired
    private FoodRepository foodRepository;

    private MenuMapper menuMapper;

    public MenuEntity createFood(FoodEntity food, long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

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

    public UserMenuDTO getUserMenuByRestaurantId(long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return menuMapper.toUserMenuDTO(menu);
    }

    public ManagerMenuDTO getManagerMenuByRestaurantId(long restId) throws ChangeSetPersister.NotFoundException {
        MenuEntity menu = menuRepository.findMenuEntityByRestaurantId(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return menuMapper.toManagerMenuDTO(menu);
    }
}
