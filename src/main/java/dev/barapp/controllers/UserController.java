package dev.barapp.controllers;

import dev.barapp.DTOs.user.*;
import dev.barapp.service.MenuService;
import dev.barapp.service.OrderService;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/restaurants")
    public List<UserCardRestDTO> getRestaurantsCards() throws ChangeSetPersister.NotFoundException {
        return restaurantService.getUserRestCards();
    }

    @GetMapping("/restaurant")
    public UserRestDTO getRestaurantById(@RequestParam(value = "restId") long id) throws ChangeSetPersister.NotFoundException {
        return restaurantService.getUserRestaurantById(id);
    }

    @GetMapping("/menu")
    public UserMenuDTO getMenu(@RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return menuService.getUserMenuByRestaurantId(restId);
    }

    @PostMapping("/create/order")
    public UserNewOrderDTO createOrder(@RequestBody UserNewOrderDTO order, @RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return orderService.createOrder(restId, order);
    }

    @GetMapping("/orders")
    public List<UserOrderDTO> getOrdersByUserId(@RequestParam(value = "userId") long userId) throws ChangeSetPersister.NotFoundException {
        return orderService.getUserOrdersByUserId(userId);
    }
}
