package dev.barapp.service;

import dev.barapp.DTOs.CardRestEntityDTO;
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

    public List<CardRestEntityDTO> getAllRestCards(){
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();

        List<CardRestEntityDTO> cardRestEntityDTOS = new ArrayList<>();

        for (RestaurantEntity restaurantEntity : restaurantEntities) {
            cardRestEntityDTOS.add(CardRestEntityDTO.builder()
                            .id(restaurantEntity.getId())
                            .name(restaurantEntity.getName())
                            .preview(restaurantEntity.getImg().get(0))
                            .rating(restaurantEntity.getRating())
                    .build());
        }

        return cardRestEntityDTOS;
    }

    public Optional<RestaurantEntity> getRestaurantById(Long id){
        return restaurantRepository.findById(id);
    }
}
