package dev.barapp.DTOs.manager;

import dev.barapp.entities.ImageEntity;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ManagerCreateFoodDTO {
    private String name;
    private List<ImageEntity> img;
    private String description;
    private BigDecimal price;
}
