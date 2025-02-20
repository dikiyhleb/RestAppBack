package dev.barapp.DTOs.waiter;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.TableEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record WaiterNewOrderDTO(List<FoodEntity> foods, Date date, String userEmail, TableEntity table, BigDecimal total, long waiterId) {
}
