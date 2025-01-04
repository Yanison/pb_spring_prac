package com.pb.starter.component.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;

@Controller
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "error";
    }

    @ExceptionHandler(LoginException.class)
    public String handleLoginException(LoginException e) {
        return "loginError";
    }
}
