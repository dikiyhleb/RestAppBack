package dev.barapp.DTOs;


import dev.barapp.entities.ImageEntity;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CardRestEntityDTO {
    private Long id;
    private String name;
    private ImageEntity preview;
    private BigDecimal rating;
}
