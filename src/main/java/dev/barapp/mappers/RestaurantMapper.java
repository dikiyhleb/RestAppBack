package dev.barapp.mappers;

import dev.barapp.DTOs.ManagerRestDTO;
import dev.barapp.entities.RestaurantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    ManagerRestDTO restToManagerRestDTO(RestaurantEntity restaurantEntity);
}
