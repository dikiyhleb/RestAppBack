package dev.barapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerEntity extends BaseUserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "credential_id")
    private CredentialEntity credentialEntity;

    private String name;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
}
