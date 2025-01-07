package com.pb.starter.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity {
    private Long id;
    private String email;
    private String password;
    private String name;
    private int gender;
    private String age;
    private String grantedAuth;
    private String tel;
    private LocalDateTime regDt;
    private LocalDateTime modDt;

    @Builder
    public UserEntity(Long id, String email, String password, String name,int gender, String age, String tel,String grantedAuth) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.gender = gender;
        this.grantedAuth = grantedAuth;
    }
}
