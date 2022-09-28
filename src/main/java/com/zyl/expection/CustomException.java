package com.zyl.expection;

import org.springframework.security.core.AuthenticationException;

public class CustomException extends AuthenticationException {
    public CustomException(String message) {
        super(message);
    }
}
