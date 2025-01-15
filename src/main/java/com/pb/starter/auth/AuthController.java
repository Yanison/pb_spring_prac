package com.pb.starter.auth;

import com.pb.starter.component.GlobalAdvice;
import com.pb.starter.component.exception.UserDuplicationException;
import com.pb.starter.model.UserEntity;
import com.pb.starter.model.api.SignUpRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final GlobalAdvice globalAdvice;
    private final AuthService authService;

    @GetMapping("/login/form")
    public String formLogin() {
        return "auth/login/authentication-form-login1";
    }
    @GetMapping("/login")
    public String login() {
        return "auth/login/authentication-login1";
    }

    @PostMapping("/login")
    public String loginRequest(UserEntity loginRequest, HttpSession session) throws Exception {
        Optional<UserEntity> ue = authService.findUserByEmailAndPassword(loginRequest);
        if (ue.isPresent()) {
            session.setAttribute("user", ue.get());
            return "redirect:/subject";
        }else{
            return "redirect:/login#failed";
        }
    }

    @GetMapping("/signUp/form")
    public String signUp() {
        return "auth/register/authentication-register1";
    }

    @PostMapping("/signUp")
    public String signUpRequest(SignUpRequest signUpRequest) {
        try {
            int ret = authService.insertUser(signUpRequest.toEntity());
            return ret > 0 ? "redirect:/login" : "redirect:/signUp";
        }catch (UserDuplicationException e){
            return "redirect:/signUp#failed_dupplicated";
        }
    }
}
