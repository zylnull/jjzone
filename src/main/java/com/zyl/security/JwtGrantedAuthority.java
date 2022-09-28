package com.zyl.security;

import org.springframework.security.core.GrantedAuthority;


public class JwtGrantedAuthority implements GrantedAuthority {

    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public JwtGrantedAuthority(String authority) {
        this.authority = authority;
    }
}
