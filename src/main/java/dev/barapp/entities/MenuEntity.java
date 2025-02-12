package dev.barapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodEntity> foods;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    public void addFood(FoodEntity food) {
        foods.add(food);
        food.setMenu(this);
    }
}
