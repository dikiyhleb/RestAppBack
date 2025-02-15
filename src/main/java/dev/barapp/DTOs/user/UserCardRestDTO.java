package dev.barapp.DTOs.user;

import dev.barapp.entities.ImageEntity;

import java.math.BigDecimal;

public record UserCardRestDTO(long id, String name, ImageEntity preview, BigDecimal rating) {
}
