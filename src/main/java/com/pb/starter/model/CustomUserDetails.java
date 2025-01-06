package com.pb.starter.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Data
public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final String name;
    private final String gender;
    private final int age;
    private final String tel;
    private String grantedAuth;
    private final String refreshToken;
    private LocalDateTime regDt;
    private LocalDateTime modDt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles(this.grantedAuth);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .grantedAuth(this.grantedAuth)
                .build();
    }

    private Collection<SimpleGrantedAuthority> getRoles(String roles){
        if(!StringUtils.hasText(roles)){ throw new IllegalArgumentException("### roles is empty"); }
        String[] roleArray = roles.split("#");
        if(roleArray.length < 2){
            return Collections.singleton(new SimpleGrantedAuthority(roleArray[0]));
        }else{
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(String role : roleArray){
                if(!StringUtils.hasText(role)){ throw new IllegalArgumentException("### role is empty"); }
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return authorities;
        }
    }
}
