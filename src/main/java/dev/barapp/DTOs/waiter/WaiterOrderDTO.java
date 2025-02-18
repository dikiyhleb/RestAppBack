package dev.barapp.DTOs.waiter;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.TableEntity;
import dev.barapp.entities.UserEntity;
import dev.barapp.entities.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record WaiterOrderDTO(long id, OrderStatus status, Date date,
                             BigDecimal rating, BigDecimal total, List<FoodEntity> foods,
                             UserEntity user, TableEntity table) {
}
