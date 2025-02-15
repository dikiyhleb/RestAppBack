package dev.barapp.DTOs.waiter;

import dev.barapp.entities.MenuEntity;
import dev.barapp.entities.TableEntity;

import java.util.List;

public record WaiterRestDTO(long id, String name, MenuEntity menu) {
}
