package dev.barapp.service;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;

    public WaiterEntity findAvailableWaiterByRestId(long restId) {
        List<WaiterEntity> waiters = waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);

        return waiters.get(0);
    }
}
