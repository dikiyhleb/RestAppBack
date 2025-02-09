package dev.barapp.controllers;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.repositories.ManagerRepository;
import dev.barapp.repositories.RestaurantRepository;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    @Autowired
    private final ManagerRepository managerRepository;
    @Autowired
    private WaiterRepository waiterRepository;

    @GetMapping("/restaurant")
    public RestaurantEntity getRestaurant(@RequestParam(value = "managerId") long id) {
        return managerRepository.findRestaurantEntityByManagerEntity_Id(id);
    }

    @GetMapping("/waiters")
    public List<WaiterEntity> getWaiters(@RequestParam(value = "restId") long id) {
        return waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(id);
    }
}
