package com.pb.starter.model.api;

import com.pb.starter.model.UserEntity;
import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String name;
    private int gender;
    private String age;
    private String tel;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .age(this.age)
                .tel(this.tel)
                .build();
    }
}
