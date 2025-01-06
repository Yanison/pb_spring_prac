package com.pb.starter.auth;

import com.pb.starter.component.exception.UserDuplicationException;
import com.pb.starter.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService{

    private final AuthMapper authMapper;

    public Optional<UserEntity> findUserByEmailAndPassword(UserEntity lr) {
        log.info("### AuthMapper findUserByEmailAndPassword");
        return authMapper.findUserByEmailAndPassword(lr);
    }

    public int insertUser(UserEntity user) {
        log.info("### AuthMapper insertUser");
        Optional<UserEntity> found = authMapper.findUserByEmail(user.getEmail());
        if(found.isPresent()){ throw new UserDuplicationException("### User already exists"); }
        return authMapper.insertUser(user);
    }
}
