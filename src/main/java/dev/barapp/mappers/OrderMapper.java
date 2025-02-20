package dev.barapp.mappers;

import dev.barapp.DTOs.user.UserNewOrderDTO;
import dev.barapp.DTOs.waiter.WaiterNewOrderDTO;
import dev.barapp.DTOs.waiter.WaiterOrderDTO;
import dev.barapp.DTOs.waiter.WaiterPreviewOrderDTO;
import dev.barapp.entities.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    UserNewOrderDTO toUserNewOrderDTO(OrderEntity order);

    List<WaiterPreviewOrderDTO> toWaiterPreviewOrderDTOs(List<OrderEntity> orders);

    WaiterOrderDTO toWaiterOrderDTO(OrderEntity order);

    WaiterNewOrderDTO toWaiterNewOrderDTO(OrderEntity order);
}
