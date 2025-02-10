package dev.barapp.controllers;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.TableEntity;
import dev.barapp.repositories.TableRepository;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterRepository waiterRepository;
    private final TableRepository tableRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/restaurant")
    public RestaurantEntity getRestaurantByWaiterId(@RequestParam(value = "waiterId") long waiterId){
        return waiterRepository.findRestaurantEntityByWaiterEntity_Id(waiterId)
                .orElseThrow(() -> new RuntimeException("Waiter not found"));
    }

    @GetMapping("/tables")
    public TableEntity[] getTablesByRestaurantId(@RequestParam(value = "restId") long restId) {
        return tableRepository.findTableEntitiesByRestaurantId(restId).orElseThrow(() -> new RuntimeException("Tables not found!"));
    }
}
