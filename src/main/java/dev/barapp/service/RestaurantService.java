package dev.barapp.service;

import dev.barapp.DTOs.manager.ManagerRestDTO;
import dev.barapp.DTOs.user.UserCardRestDTO;
import dev.barapp.DTOs.user.UserRestDTO;
import dev.barapp.DTOs.waiter.WaiterRestDTO;
import dev.barapp.DTOs.waiter.WaiterTablesDTO;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.mappers.RestaurantMapper;
import dev.barapp.mappers.TableMapper;
import dev.barapp.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final TableMapper tableMapper;

    public List<UserCardRestDTO> getUserRestCards() throws ChangeSetPersister.NotFoundException {
        List<RestaurantEntity> rests = restaurantRepository.findAll();

        if(rests.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }

        return restaurantMapper.toUserCardRestDTO(rests);
    }

    public UserRestDTO getUserRestaurantById(Long id) throws ChangeSetPersister.NotFoundException {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return restaurantMapper.toUserRestDTO(restaurant);
    }

    public WaiterTablesDTO getWaiterTablesByRestaurantId(Long id) throws ChangeSetPersister.NotFoundException {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return tableMapper.toWaiterTablesDTO(restaurant);
    }

    public ManagerRestDTO findRestaurantByManagerEntityId(Long id) throws ChangeSetPersister.NotFoundException {
        RestaurantEntity rest = restaurantRepository.findRestaurantEntityByManagerId(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return restaurantMapper.toManagerRestDTO(rest);
    }

    public WaiterRestDTO findRestaurantByWaiterEntityId(Long id) throws ChangeSetPersister.NotFoundException {
        RestaurantEntity rest = restaurantRepository.findRestaurantEntityByWaitersId(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return restaurantMapper.toWaiterRestDTO(rest);
    }
}
