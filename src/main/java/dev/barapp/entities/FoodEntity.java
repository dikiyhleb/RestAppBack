package dev.barapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> img;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
