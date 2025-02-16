package dev.barapp.controllers;

import dev.barapp.DTOs.waiter.WaiterRestDTO;
import dev.barapp.DTOs.waiter.WaiterTablesDTO;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.mappers.RestaurantMapper;
import dev.barapp.mappers.TableMapper;
import dev.barapp.repositories.TableRepository;
import dev.barapp.repositories.WaiterRepository;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final RestaurantService restaurantService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/restaurant")
    public WaiterRestDTO getRestaurantByWaiterId(@RequestParam(value = "waiterId") long waiterId) throws ChangeSetPersister.NotFoundException {
        return restaurantService.findRestaurantByWaiterEntityId(waiterId);
    }

    @GetMapping("/tables")
    public WaiterTablesDTO getTablesByRestaurantId(@RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        return restaurantService.getWaiterTablesByRestaurantId(restId);
    }
}
