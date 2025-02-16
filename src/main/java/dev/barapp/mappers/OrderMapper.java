package dev.barapp.mappers;

import dev.barapp.DTOs.user.UserNewOrderDTO;
import dev.barapp.entities.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    UserNewOrderDTO toUserNewOrderDTO(OrderEntity order);
}
