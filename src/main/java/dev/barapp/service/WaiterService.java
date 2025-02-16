package dev.barapp.service;

import dev.barapp.DTOs.manager.ManagerWaiterDTO;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.mappers.WaiterMapper;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;
    private final WaiterMapper waiterMapper;

    public WaiterEntity findAvailableWaiterByRestId(long restId) {
        List<WaiterEntity> waiters = waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);

        return waiters.get(0);
    }

    public List<ManagerWaiterDTO> findWaitersByRestaurantId(long restId) throws ChangeSetPersister.NotFoundException {
        List<WaiterEntity> waiters = waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);

        if (waiters.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        return waiterMapper.toManagerWaitersDTO(waiters);
    }
}
