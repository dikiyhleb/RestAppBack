package dev.barapp.DTOs.user;

import dev.barapp.entities.FoodEntity;

import java.util.List;

public record UserMenuDTO(long id, List<FoodEntity> foods) {
}
