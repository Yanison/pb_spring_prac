package com.pb.starter.auth;

import com.pb.starter.model.UserEntity;
import com.pb.starter.model.api.CustomApiResponse;
import com.pb.starter.model.api.LoginRequest;
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
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse res) {
        try {
            log.info("loginRequest: {}", loginRequest);
            String token = authService.jwtLogin(loginRequest.toEntity());
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

    @PostMapping("/login/basic")
    public ResponseEntity loginRequest(@RequestBody LoginRequest loginRequest){
        Optional<UserEntity> ue = authService.findUserByEmailAndPassword(loginRequest.toEntity());
        boolean ret = ue.isPresent();
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(ret ? ue.get() : null)
                        .status(ret ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                        .build()
        );
    }
}
