package com.pb.starter.component.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.starter.component.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static com.pb.starter.component.util.CommonUtil.isStartWith;
import static com.pb.starter.model.constant.SecurityEnum.SecurityFreeURL.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // Swagger나 로그인 등 특정 URL에 대해 필터 제외
        String requestURI = httpServletRequest.getRequestURI();

        if( isStartWith(requestURI, FILTER_FREE_URL() )){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);

        // 토큰이 유효하다면
        if (token != null && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            ObjectMapper objectMapper = new ObjectMapper();

            ResponseEntity<?> result = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 존재하지 않습니다.");

            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
