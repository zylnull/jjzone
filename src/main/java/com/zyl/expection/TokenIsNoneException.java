package com.zyl.expection;


import org.springframework.security.core.AuthenticationException;

public class TokenIsNoneException extends AuthenticationException {
    public TokenIsNoneException(String msg) {
        super(msg);
    }
}
