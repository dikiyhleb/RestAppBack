package dev.barapp.mappers;

import dev.barapp.DTOs.waiter.WaiterTablesDTO;
import dev.barapp.entities.RestaurantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    WaiterTablesDTO toWaiterTablesDTO(RestaurantEntity restaurantEntity);
}
