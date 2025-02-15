package dev.barapp.DTOs.manager;

import dev.barapp.entities.FoodEntity;

import java.util.List;

public record ManagerMenuDTO(long id, List<FoodEntity> foods) {
}
