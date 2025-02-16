package dev.barapp.mappers;

import dev.barapp.DTOs.manager.ManagerWaiterDTO;
import dev.barapp.entities.WaiterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WaiterMapper {
    @Mapping(source = "credentialEntity.email", target = "email")
    ManagerWaiterDTO toManagerWaiterDTO(WaiterEntity waiterEntity);

    List<ManagerWaiterDTO> toManagerWaitersDTO(List<WaiterEntity> waiterEntities);
}
