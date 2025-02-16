package dev.barapp.DTOs.user;

import dev.barapp.entities.FoodEntity;
import dev.barapp.entities.TableEntity;
import dev.barapp.entities.UserEntity;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class UserNewOrderDTO {
    private List<FoodEntity> foods;
    private Date date;
    private UserEntity user;
    private TableEntity table;
}
