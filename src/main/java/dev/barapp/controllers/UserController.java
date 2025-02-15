package dev.barapp.controllers;

import dev.barapp.DTOs.user.UserCardRestDTO;
import dev.barapp.DTOs.user.UserMenuDTO;
import dev.barapp.DTOs.user.UserRestDTO;
import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.MenuEntity;
import dev.barapp.entities.OrderEntity;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.mappers.MenuMapper;
import dev.barapp.mappers.RestaurantMapper;
import dev.barapp.repositories.RestaurantRepository;
import dev.barapp.service.MenuService;
import dev.barapp.service.OrderService;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final RestaurantMapper restaurantMapper;
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final OrderService orderService;
    private final MenuMapper menuMapper;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/restaurants")
    public List<UserCardRestDTO> getRestaurants() {
        List<RestaurantEntity> rests = restaurantService.getAllRestaurants();

        return restaurantMapper.restToUserCardRestDTO(rests);
    }

    @GetMapping("/restaurant")
    public UserRestDTO getRestaurantById(@RequestParam(value = "restId") long id) throws ChangeSetPersister.NotFoundException {
        Optional<RestaurantEntity> restaurant = restaurantService.getRestaurantById(id);

        RestaurantEntity entity = restaurant.orElseThrow(ChangeSetPersister.NotFoundException::new);

        return restaurantMapper.restToUserRestDTO(entity);
    }

    @GetMapping("/menu")
    public UserMenuDTO getMenu(@RequestParam(value = "restId") long restId) {
        MenuEntity menu = menuService.getMenuByRestaurantId(restId);

        return menuMapper.toUserMenuDTO(menu);
    }

    @PostMapping("/create/order")
    public OrderEntity createOrder(@RequestBody OrderEntity order, @RequestParam(value = "restId") long restId) {
        return orderService.createOrder(restId, order);
    }
}
