package dev.barapp.DTOs;


import dev.barapp.entities.ImageEntity;

import java.math.BigDecimal;


public record ManagerRestDTO(long id, String name, BigDecimal rating, ImageEntity[] img) {
}
