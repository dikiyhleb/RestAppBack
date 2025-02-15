package dev.barapp.controllers;

import dev.barapp.DTOs.ManagerRestDTO;
import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.MenuEntity;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.mappers.RestaurantMapper;
import dev.barapp.repositories.ManagerRepository;
import dev.barapp.repositories.WaiterRepository;
import dev.barapp.service.MenuService;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    @Autowired
    private final ManagerRepository managerRepository;
    @Autowired
    private final WaiterRepository waiterRepository;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantMapper restaurantMapper;

    @GetMapping("/restaurant")
    public ManagerRestDTO getRestaurant(@RequestParam(value = "managerId") long id) {
        RestaurantEntity rest = restaurantService.findRestaurantByManagerEntityId(id);

        return restaurantMapper.restToManagerRestDTO(rest);
    }

    @GetMapping("/waiters")
    public List<WaiterEntity> getWaiters(@RequestParam(value = "restId") long id) {
        return waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(id);
    }

    @PostMapping("/create/food")
    public MenuEntity createFood(@RequestBody(required = true) FoodEntity foodEntity, @RequestParam(value = "restId") long restId) {
        return menuService.createFood(foodEntity, restId);
    }

    @DeleteMapping("/delete/food/{id}")
    public void deleteFood(@PathVariable("id") long id) {
        menuService.deleteFood(id);
    }

    @GetMapping("/menu")
    public List<FoodEntity> getMenu(@RequestParam(value = "restId") long restId) {
        return menuService.getAllFood(restId);
    }
}
