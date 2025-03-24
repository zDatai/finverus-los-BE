package com.zdatai.finverus.service.impl.user;

import com.zdatai.finverus.dto.user.LoginResultDTO;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.service.user.AuthenticationService;
import com.zdatai.finverus.utility.FinVerusJWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private FinVerusJWTUtil finVerusJwtUtil;

    final HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();

    @Override
    public LoginResultDTO authenticate(String userName, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws FinVerusException {

        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(userName, password);
        try{
            Authentication authentication = authManager.authenticate(userAuthentication);
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            contextRepository.saveContext(context, request, response);
            log.info("## Auth User : {} ##", authentication.getPrincipal());


            // Store user ID in session
            session.setAttribute("LOGGED_IN_USER_USER_NAME", userName);
//            System.out.println("session is:"+session.getAttribute("LOGGED_IN_USER_USER_NAME"));
        } catch (BadCredentialsException e) {
            throw new FinVerusException(e.getMessage());
        }
        session.setMaxInactiveInterval(finVerusJwtUtil.getSessiontimeout());
        return new LoginResultDTO(finVerusJwtUtil.generateToken(userName, session), finVerusJwtUtil.getSessiontimeout());
    }
}
