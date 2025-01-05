package com.pb.starter.component.config;

import com.pb.starter.component.TokenProvider;
import com.pb.starter.component.filter.JwtAuthenticationFilter;
import com.pb.starter.component.filter.JwtExceptionFilter;
import com.pb.starter.component.handler.JwtAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.pb.starter.model.constant.SecurityEnum.SecurityFreeURL.PERMIT_ALL_URL;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final String[] allowedUrls = {"/swagger-ui/**", "/v3/**", "/api/hrInformationManagement/login"};
    private final TokenProvider tokenProvider;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호를 인코딩하는데 사용되는 BCryptPasswordEncoder를 반환
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .cors(AbstractHttpConfigurer::disable)// CORS 설정 -> 비활성화 : https://docs.spring.io/spring-security/reference/reactive/integrations/cors.html
                .csrf(AbstractHttpConfigurer::disable)// CSRF 보호 비활성화
                .formLogin(AbstractHttpConfigurer::disable)// Form 로그인 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)// http basic 인증 방식 비활성화
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("Authorization")) //로그아웃 설정
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))// X-Frame-Options, iframe 사용범위 설정: 클릭재킹 공격 방어
                //허용 경로 작업
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers( PERMIT_ALL_URL() ).permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().authenticated()
                )
                //JWT 인증 필터 추가 addFilterBefore(filer, beforeFilter), UsernamePasswordAuthenticationFilter 전에 JwtAuthenticationFilter 추가
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class)
                .exceptionHandling(handling ->
                        handling
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));       // Session 사용하지 않음

        return http.build();
    }


}
