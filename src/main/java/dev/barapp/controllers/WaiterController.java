package dev.barapp.controllers;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterRepository waiterRepository;

    @PostMapping("/restaurant")
    public Optional<RestaurantEntity> getRestaurantByWaiterId(@RequestParam(value = "waiterId") Long waiterId){
        return waiterRepository.findRestaurantEntityByWaiterEntity_Id(waiterId);
    }
}
