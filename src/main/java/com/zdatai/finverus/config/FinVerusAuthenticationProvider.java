package com.zdatai.finverus.config;

import com.zdatai.finverus.dto.user.FinVerusUserDetail;
import com.zdatai.finverus.repository.user.AuthenticationDAO;
import com.zdatai.finverus.utility.PasswordEncryptionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class FinVerusAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationDAO authenticationDAO;

    @Autowired
    private PasswordEncryptionUtility passwordEncryptionUtility;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FinVerusUserDetail userAccount = authenticationDAO.getUserAccountByName(authentication.getPrincipal().toString());
        if(userAccount == null){
            throw new BadCredentialsException("Invalid Credentials");
        }
        if(!userAccount.getPasscode().equals(passwordEncryptionUtility.encryptPassword(authentication.getCredentials().toString()))){
            throw new BadCredentialsException("Invalid Credentials");
        }
        return new FinVerusAuthToken(userAccount.getUsername(), null, userAccount.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
