package dev.barapp.DTOs.user;

import dev.barapp.entities.TableEntity;

import java.util.List;

public record UserRestDTO(long id, String name, List<TableEntity> tables) {
}
