package com.zyl.expection;

import org.springframework.security.core.AuthenticationException;

public class JwtParseErrorException extends AuthenticationException {

    public JwtParseErrorException(String msg) {
        super(msg);
    }
}
