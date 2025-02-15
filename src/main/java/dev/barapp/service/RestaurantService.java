package dev.barapp.service;

import dev.barapp.entities.RestaurantEntity;
import dev.barapp.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantEntity> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    public Optional<RestaurantEntity> getRestaurantById(Long id){
        return restaurantRepository.findById(id);
    }

    public RestaurantEntity findRestaurantByManagerEntityId(Long id){
        return restaurantRepository.findRestaurantEntityByManagerId(id);
    }

    public RestaurantEntity findRestaurantByWaiterEntityId(Long id){
        return restaurantRepository.findRestaurantEntityByWaitersId(id);
    }
}
