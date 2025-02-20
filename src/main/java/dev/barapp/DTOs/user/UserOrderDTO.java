package dev.barapp.DTOs.user;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.TableEntity;
import dev.barapp.entities.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record UserOrderDTO(long id, OrderStatus status, Date date, BigDecimal rating, List<FoodEntity> foods, TableEntity table, BigDecimal total) {
}
