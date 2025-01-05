package com.pb.starter.auth;

import com.pb.starter.model.CustomUserDetails;
import com.pb.starter.model.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface AuthMapper {

    @Select("SELECT email,password FROM user WHERE email = #{email} AND password = #{password}")
    Optional<UserEntity> findUserByEmailAndPassword(UserEntity lr);

    @Insert(""" 
        INSERT INTO user 
            (
            email,
            password,
            name,
            gender,
            age,
            tel
            ) 
        VALUES ( 
        #{email},
        #{password},
        #{name},
        #{gender},
        #{age},
        #{tel}
        )
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(UserEntity user);

    @Select("SELECT * FROM user WHERE email = #{email}")
    Optional<CustomUserDetails> findUserByEmail(String email);
}
