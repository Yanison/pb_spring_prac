# [Spring Security]

Spring Security는 강력하고 매우 유연하게 커스터마이징 가능한 인증/인가 `프레임워크` 입니다.
Spring 기반의 어플리케이션을 보호하는 실질적인 표준이 되었습니다.

Spring Security는 자바 어플리케이션에서 인증인가와 관련된 기능에 초점을 춘 프레임워크 입니다.
모든 Spring 프로젝트에서 Spring Security의 real power는 고객의 요구사항에 맞게 쉽고 강력한 확장성을 제공합니다.


## Features:
- #### 인증(Authentication)과 권한 부여(Authorization)에 대한 포괄적이고 확장 가능한 지원
  - Spring Security는 다양한 인증 방법(예: 폼 로그인, OAuth2, SAML 등)과 세부적인 권한 설정(예: 역할 기반 접근 제어 등)을 지원합니다.
  - 사용자 필요에 따라 기능을 확장하거나 커스터마이징 할 수 있도록 설계되어 있습니다.
- #### 세션 고정(session fixation), 클릭재킹(clickjacking), 교차 사이트 요청 위조(CSRF) 등과 같은 공격으로부터 보호
  - Spring Security는 웹 애플리케이션의 일반적인 보안 취약점에 대한 기본적인 방어 기능을 제공합니다.
    - Session Fixation : 세션 탈취 공격 방지
    - Clickjacking : X-Frame-Options 헤더를 통한 클릭재킹 방지
    - CSRF : CSRF 토큰을 통한 교차 사이트 요청 위조 방지
    - 기타 보안 위협에 대한 보호.
- #### Servlet API와 통합
  - Spring Security는 Servlet API와 밀접하게 통합되어, 필터 체인(Filter Chain)을 통해 HTTP 요청과 응답을 제어합니다.
  - 웹 애플리케이션의 다양한 요청에 대해 보안 정책을 쉽게 적용할 수 있습니다.
- #### Spring Web MVC와의 선택적 통합
  - Spring Security는 Spring Web MVC 애플리케이션과 자연스럽게 통합되어 컨트롤러 레벨에서 @PreAuthorize, @Secured 등의 애노테이션을 사용하여 보안 규칙을 정의할 수 있습니다.
  - 필요에 따라 Spring Web MVC가 아닌 다른 프레임워크와도 사용할 수 있습니다.

    

## Study 방향성
Spring Security 기반에서 JWT를 사용하여 인증/인가를 구현하는 방법을 학습합니다.
Spring Application에서 JWT를 어떻게 생성하고 클라이언트에 제공하는지, 
그리고 JWT에 담긴 정보들로 어떻게 Spring Security에서 보안규칙을 적용할 수 있는지 학습하는것이 목표입니다.


[Spring Security]:(https://spring.io/projects/spring-security)