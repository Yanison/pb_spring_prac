package com.pb.starter.auth;

import com.pb.starter.model.UserEntity;
import com.pb.starter.model.api.LoginRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AuthMapper {

    @Select("SELECT * FROM user")
    List<UserEntity> selectUserList();
    @Select("SELECT * FROM user WHERE id = #{id}")
    Optional<UserEntity> selectUser(Long id);

    @Select("SELECT email,password FROM user WHERE email = #{email} AND password = #{password}")
    Optional<UserEntity> findUserByEmailAndPassword(UserEntity lr);
    @Update("""
    UPDATE user 
    SET 
        email = #{email},
        password = #{password},
        gender = #{gender},
        age = #{age},
        tel = #{tel}
    WHERE id = #{id}
    """)
    int updateUser(UserEntity user);

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
    Optional<UserEntity> findUserByEmail(String email);
}
