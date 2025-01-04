package com.pb.starter.model.constant;

import lombok.Getter;

public class SecurityEnum {
    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @Getter
    public enum SecurityFreeURL {
        LOGIN_API("/api/v1/auth/login"),
        SIGNUP("/signUp"),
        LOGIN("/login"),
        ASSETS("/assets/**"),
        DIST("/dist/**"),
        FAVICON("/favicon.ico"),
        ERROR("/error"),
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
