package dev.barapp.DTOs.manager;


import dev.barapp.entities.ImageEntity;

import java.math.BigDecimal;


public record ManagerRestDTO(long id, String name, BigDecimal rating, ImageEntity[] img) {
}
