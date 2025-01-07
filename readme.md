# Spring Security6 + JWT토큰 인증을 활용한 로그인 구현.

https://github.com/Yanison/pb_spring_prac/tree/starter-session-1.0.1-SNAPSHOT/readme
에 내용 정리했습니다. 참고해주세요~

## 개요
### 1. JWT 토큰
### 2. Spring Security
## 프로젝트 구현
### 0. 밑작업
#### 1. 환경구성
#### 2. MyBatis 설정
#### 2. User 정의
### 1.Spring Security 설정
#### 2. SecurityConfig.java
#### 4. UserDetails
#### 5. UserDetailsService
### 2. JWT 토큰 생성
#### 1. TokenProvider.java
#### 2. wtAuthenticationFilter.java
#### 3. JwtExceptionFilter.java
#### 4. JwtAccessDeniedHandler.java