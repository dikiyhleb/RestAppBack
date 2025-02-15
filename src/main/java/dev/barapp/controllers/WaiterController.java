package dev.barapp.controllers;

import dev.barapp.DTOs.waiter.WaiterRestDTO;
import dev.barapp.DTOs.waiter.WaiterTablesDTO;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.TableEntity;
import dev.barapp.mappers.RestaurantMapper;
import dev.barapp.mappers.TableMapper;
import dev.barapp.repositories.TableRepository;
import dev.barapp.repositories.WaiterRepository;
import dev.barapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterRepository waiterRepository;
    private final TableRepository tableRepository;
    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;
    private final TableMapper tableMapper;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/restaurant")
    public WaiterRestDTO getRestaurantByWaiterId(@RequestParam(value = "waiterId") long waiterId){
        RestaurantEntity rest = restaurantService.findRestaurantByWaiterEntityId(waiterId);

        return restaurantMapper.restToWaiterRestDTO(rest);
    }

    @GetMapping("/tables")
    public WaiterTablesDTO getTablesByRestaurantId(@RequestParam(value = "restId") long restId) throws ChangeSetPersister.NotFoundException {
        Optional<RestaurantEntity> rest = restaurantService.getRestaurantById(restId);

        RestaurantEntity restaurant = rest.orElseThrow(ChangeSetPersister.NotFoundException::new);

        return tableMapper.toWaiterTablesDTO(restaurant);
    }
}
