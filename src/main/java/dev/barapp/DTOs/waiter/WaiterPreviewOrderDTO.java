package dev.barapp.DTOs.waiter;

import dev.barapp.entities.TableEntity;
import dev.barapp.entities.enums.OrderStatus;

import java.math.BigDecimal;
public record WaiterPreviewOrderDTO(long id, OrderStatus status, BigDecimal total, TableEntity table) {
}
