package com.pb.starter.component.constant;

import lombok.Getter;

public class SecurityConstant {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Getter
    public enum SecurityFreeURL {
        LOGIN_API("/api/v1/auth/login"),
        LOGIN_API2("/api/v1/auth/login/**"),
        SIGNUP("/signUp"),
        LOGIN("/login"),
        LOGIN_FORM("/login/form"),
        ASSETS("/assets/**"),
        DIST("/dist/**"),
        FAVICON("/favicon.ico"),
        ERROR("/error"),
        SWAGGER("/swagger-ui/**"),
        SWAGGER_V3("/v3/**"),
        ;

        private final String url;

        SecurityFreeURL(String url) {
            this.url = url;
        }
        public static String[] PERMIT_ALL_URL() {
            SecurityFreeURL[] values = SecurityFreeURL.values();
            String[] urls = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                urls[i] = values[i].getUrl();
            }
            return urls;
        }

        public static String[] FILTER_FREE_URL() {
            SecurityFreeURL[] values = SecurityFreeURL.values();
            String[] urls = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                urls[i] = values[i].getUrl().replace("/**", "");
            }
            return urls;
        }
    }
}