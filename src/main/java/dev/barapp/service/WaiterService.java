package dev.barapp.service;

import dev.barapp.entities.WaiterEntity;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;

    public WaiterEntity findAvailableWaiterByRestId(long restId) {
        List<WaiterEntity> waiters = waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);

        return waiters.get(0);
    }

    public List<WaiterEntity> findWaitersByRestaurantId(long restId) {
        return waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);
    }
}
