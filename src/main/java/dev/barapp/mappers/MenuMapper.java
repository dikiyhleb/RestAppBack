package dev.barapp.mappers;


import dev.barapp.DTOs.manager.ManagerMenuDTO;
import dev.barapp.DTOs.user.UserMenuDTO;
import dev.barapp.entities.MenuEntity;
import org.mapstruct.Mapper;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    ManagerMenuDTO toManagerMenuDTO(MenuEntity menu);

    UserMenuDTO toUserMenuDTO(MenuEntity menu);
}
