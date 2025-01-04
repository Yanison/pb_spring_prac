package com.pb.starter.auth;

import com.pb.starter.component.util.CommonUtil;
import com.pb.starter.model.CustomUserDetails;
import com.pb.starter.model.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final AuthMapper authMapper;

    public List<UserEntity> selectUserList() {
        log.info("### AuthMapper selectUserList");
        return authMapper.selectUserList();
    }

    public UserEntity selectUser(Long id) throws Exception {
        log.info("### AuthMapper selectUser");
        Optional<UserEntity> found = authMapper.selectUser(id);
        return found.orElseThrow(() -> new NotFoundException("### User not found"));
    }

    public Optional<UserEntity> findUserByEmailAndPassword(UserEntity lr) throws Exception {
        log.info("### AuthMapper findUserByEmailAndPassword");

        return authMapper.findUserByEmailAndPassword(lr);
    }
    public int updateUser(UserEntity user) {
        log.info("### AuthMapper updateUser");
        return authMapper.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = authMapper.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        Collection<SimpleGrantedAuthority> authorities = CommonUtil.getRoles(user.getGrantedAuth());

        return CustomUserDetails.builder()
                .USER_ID(user.getEmail())
                .USER_PASSWORD(user.getPassword())
                .roles(authorities)
                .build();
    }
}
