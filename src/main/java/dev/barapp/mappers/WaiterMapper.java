package dev.barapp.mappers;

import dev.barapp.DTOs.manager.ManagerWaiterDTO;
import dev.barapp.entities.WaiterEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WaiterMapper {
    List<ManagerWaiterDTO> toManagerWaitersDTO(List<WaiterEntity> waiterEntities);
}
