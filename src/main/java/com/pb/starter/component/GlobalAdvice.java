package com.pb.starter.component;

import com.pb.starter.component.constant.Menu;
import com.pb.starter.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAdvice {

    @ModelAttribute("menulist")
    public Menu[] menuList() {
        return Menu.values();
    }

//    @ModelAttribute("user")
//    public CustomUserDetails  user() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // 인증되지 않은 상태일 경우 null 반환
//        if (authentication == null || !authentication.isAuthenticated() ||
//                authentication.getPrincipal().equals("anonymousUser")) {
//            return null;
//        }
//
//        // 인증된 사용자 정보 반환
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof CustomUserDetails) {
//            return (CustomUserDetails) principal;
//        }
//
//        return null;
//    }
}
