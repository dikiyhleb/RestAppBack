package dev.barapp.controllers;

import dev.barapp.DTOs.CardRestEntityDTO;
import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.OrderEntity;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.repositories.RestaurantRepository;
import dev.barapp.service.MenuService;
import dev.barapp.service.OrderService;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/restaurants")
    public List<CardRestEntityDTO> getRestaurants() {
        return restaurantService.getAllRestCards();
    }

    @GetMapping("/restaurant")
    public Optional<RestaurantEntity> getRestaurantById(@RequestParam(value = "restId") long id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/menu")
    public List<FoodEntity> getMenu(@RequestParam(value = "restId") long restId) {
        return menuService.getAllFood(restId);
    }

    @PostMapping("/create/order")
    public OrderEntity createOrder(@RequestBody OrderEntity order, @RequestParam(value = "restId") long restId) {
        return orderService.createOrder(restId, order);
    }
}
