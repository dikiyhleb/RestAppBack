package dev.barapp.model;

import dev.barapp.entities.BaseUserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse<T extends BaseUserEntity> {
    private final String accessToken;
    private T data;
}
