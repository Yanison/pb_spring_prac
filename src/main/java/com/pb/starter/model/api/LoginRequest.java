package com.pb.starter.model.api;

import com.pb.starter.model.UserEntity;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .build();
    }
}
