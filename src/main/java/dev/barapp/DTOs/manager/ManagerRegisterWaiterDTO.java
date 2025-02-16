package dev.barapp.DTOs.manager;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerRegisterWaiterDTO {
    private String name;
    private String email;
    private String password;
}
