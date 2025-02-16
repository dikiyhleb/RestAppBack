package dev.barapp.controllers;

import dev.barapp.DTOs.manager.*;
import dev.barapp.security.CredentialPrincipal;
import dev.barapp.service.AuthService;
import dev.barapp.service.MenuService;
import dev.barapp.service.RestaurantService;
import dev.barapp.service.WaiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final MenuService menuService;
    private final RestaurantService restaurantService;
    private final WaiterService waiterService;
    private final AuthService authService;

    @GetMapping("/restaurant")
    public ManagerRestDTO getRestaurant(@RequestParam(value = "managerId") long id) throws ChangeSetPersister.NotFoundException {
        return restaurantService.findRestaurantByManagerEntityId(id);
    }

    @GetMapping("/waiters")
    public List<ManagerWaiterDTO> getWaitersByRestId(@RequestParam(value = "restId") long id) throws ChangeSetPersister.NotFoundException {
        return waiterService.findWaitersByRestaurantId(id);
    }

    @PostMapping("/create/food")
    public ManagerMenuDTO createFood(@RequestBody(required = true) ManagerCreateFoodDTO food, @RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return menuService.createFood(food, restId);
    }

    @DeleteMapping("/delete/food/{id}")
    public void deleteFood(@PathVariable("id") long id) {
        menuService.deleteFood(id);
    }

    @GetMapping("/menu")
    public ManagerMenuDTO getMenu(@RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return menuService.getManagerMenuByRestaurantId(restId);
    }

    @PostMapping("/register/waiter")
    public ManagerRegisterWaiterDTO registerWaiter(@RequestBody ManagerRegisterWaiterDTO waiterDTO, @RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return authService.registerWaiter(waiterDTO, restId);
    }
}
