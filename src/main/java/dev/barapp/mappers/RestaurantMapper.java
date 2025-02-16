package dev.barapp.mappers;

import dev.barapp.DTOs.manager.ManagerRestDTO;
import dev.barapp.DTOs.user.UserCardRestDTO;
import dev.barapp.DTOs.user.UserRestDTO;
import dev.barapp.DTOs.waiter.WaiterRestDTO;
import dev.barapp.entities.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    ManagerRestDTO toManagerRestDTO(RestaurantEntity restaurantEntity);

    @Mapping(
            target = "preview",
            expression = "java(restaurantEntity.getImg() != null && !restaurantEntity.getImg().isEmpty() ? restaurantEntity.getImg().get(0) : null)")
    UserCardRestDTO toUserCardRestDTO(RestaurantEntity restaurantEntity);

    List<UserCardRestDTO> toUserCardRestDTO(List<RestaurantEntity> rests);

    UserRestDTO toUserRestDTO(RestaurantEntity restaurantEntity);

    WaiterRestDTO toWaiterRestDTO(RestaurantEntity restaurantEntity);
}
