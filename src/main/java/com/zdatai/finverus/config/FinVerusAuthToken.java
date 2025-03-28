package com.zdatai.finverus.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FinVerusAuthToken extends UsernamePasswordAuthenticationToken {

    public FinVerusAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public FinVerusAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
