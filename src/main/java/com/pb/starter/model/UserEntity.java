package com.pb.starter.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;

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
    private String regDt;
    private String modDt;

    @Builder
    public UserEntity(Long id, String email, String password, String name,int gender, String age, String tel, String regDt, String modDt,String grantedAuth) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.grantedAuth = grantedAuth;
    }

    public UserEntity() {
    }
}
