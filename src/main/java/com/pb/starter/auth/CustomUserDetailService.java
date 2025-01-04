package com.pb.starter.auth;

import com.pb.starter.component.TokenProvider;
import com.pb.starter.component.exception.UserDuplicationException;
import com.pb.starter.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService{

    private final TokenProvider tokenProvider;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public String jwtLogin(UserEntity loginInfo) throws LoginException {

        UserEntity user = authMapper.findUserByEmail(loginInfo.getEmail()).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(loginInfo.getPassword(), user.getPassword())) {
            throw new LoginException("잘못된 비밀번호입니다.");
        }
        return tokenProvider.createToken(user.getEmail(), user.getGrantedAuth());
    }

    public int insertUser(UserEntity user) {
        log.info("### AuthMapper insertUser");
        Optional<UserEntity> found = authMapper.findUserByEmail(user.getEmail());
        if(found.isPresent()){ throw new UserDuplicationException("### User already exists"); }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authMapper.insertUser(user);
    }
}
