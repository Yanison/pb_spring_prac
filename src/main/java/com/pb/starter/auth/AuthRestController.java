package com.pb.starter.auth;

import com.pb.starter.model.UserEntity;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginRequest, HttpServletResponse res) {
        try {
            log.info("loginRequest: {}", loginRequest);
            String token = authService.jwtLogin(loginRequest);
            ResponseCookie cookie = ResponseCookie.from("Authorization", token)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .build();
            res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (LoginException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
