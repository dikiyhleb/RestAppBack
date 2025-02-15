package dev.barapp.controllers;

import dev.barapp.DTOs.manager.ManagerMenuDTO;
import dev.barapp.DTOs.manager.ManagerRestDTO;
import dev.barapp.DTOs.manager.ManagerWaiterDTO;
import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.MenuEntity;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.mappers.MenuMapper;
import dev.barapp.mappers.RestaurantMapper;
import dev.barapp.mappers.WaiterMapper;
import dev.barapp.repositories.ManagerRepository;
import dev.barapp.repositories.WaiterRepository;
import dev.barapp.service.MenuService;
import dev.barapp.service.RestaurantService;
import dev.barapp.service.WaiterService;
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
    @Autowired
    private WaiterService waiterService;
    @Autowired
    private WaiterMapper waiterMapper;
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/restaurant")
    public ManagerRestDTO getRestaurant(@RequestParam(value = "managerId") long id) {
        RestaurantEntity rest = restaurantService.findRestaurantByManagerEntityId(id);

        return restaurantMapper.restToManagerRestDTO(rest);
    }

    @GetMapping("/waiters")
    public List<ManagerWaiterDTO> getWaiters(@RequestParam(value = "restId") long id) {
        List<WaiterEntity> waiters = waiterService.findWaitersByRestaurantId(id);

        return waiterMapper.toManagerWaitersDTO(waiters);
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
    public ManagerMenuDTO getMenu(@RequestParam(value = "restId") long restId) {
        MenuEntity menu = menuService.getMenuByRestaurantId(restId);

        return menuMapper.toManagerMenuDTO(menu);
    }
}
