package com.pb.starter.component.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CommonUtil {

    public static boolean isStartWith(String str, String[] startWith) {
        for(String s : startWith){
            if(str.startsWith(s)){ return true; }
        }
        return false;
    }

    public static Collection<SimpleGrantedAuthority> getRoles(String roles){
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
