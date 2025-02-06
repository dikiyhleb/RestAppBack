package dev.barapp.controllers;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/restaurants")
    public List<RestaurantEntity> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
