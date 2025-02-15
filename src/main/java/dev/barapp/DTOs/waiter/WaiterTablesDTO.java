package dev.barapp.DTOs.waiter;

import dev.barapp.entities.TableEntity;

import java.util.List;

public record WaiterTablesDTO(List<TableEntity> tables) {
}
