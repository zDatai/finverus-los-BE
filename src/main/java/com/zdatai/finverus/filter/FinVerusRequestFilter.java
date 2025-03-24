package com.zdatai.finverus.filter;

import com.zdatai.finverus.exception.FinVerusAccessDeniedException;
import com.zdatai.finverus.exception.FinVerusUnAuthException;
import com.zdatai.finverus.utility.FinVerusJWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class FinVerusRequestFilter extends OncePerRequestFilter{

    private static final List<String> PRE_LOGIN_ENDPOINTS =
            Collections.unmodifiableList(Arrays.asList(
                    "token",
                    "csrf",
                    "swagger-ui",
                    "swagger-resources",
                    "swagger-config",
                    "index.html",
                    "index.css",
                    "swagger-initializer.js",
                    "favicon-32x32.png",
                    "favicon-16x16.png",
                    "api-docs"
            ));

    public static List<String> getPreLoginEndpoints() {
        return PRE_LOGIN_ENDPOINTS;
    }

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private FinVerusJWTUtil finVerusJwtUtil;

    //    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String[] requestURIPaths = request.getRequestURI().split("\\/");
        // Exclude OPTIONS method for avoid check Authorization Header on pre-flight OPTION requests
        if("OPTIONS".equalsIgnoreCase(request.getMethod()) || (requestURIPaths.length != 0 && isPreLoginRequest(requestURIPaths[requestURIPaths.length - 1]))) {
            filterChain.doFilter(request, response);
        }else {
            final String authHeader = request.getHeader("Authorization");
            //final String publicModifier = requestURIPaths.length > 1 ? requestURIPaths[requestURIPaths.length - 2] : null;
            String user = null;
            String jwt = null;
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                try {
                    jwt = authHeader.substring(7);
                    user = finVerusJwtUtil.extractUsername(jwt);
                    if(user != null && finVerusJwtUtil.validToken(jwt, user, request.getSession().getId())){
                        // load user from DB
                        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                        if(auth == null || auth instanceof AnonymousAuthenticationToken) {
                            handlerExceptionResolver.resolveException(request, response, null, new FinVerusUnAuthException("Token Expired"));
                        }else {
                            if(isPublicAccessible(requestURIPaths)){
                                request.setAttribute("authSession", auth);
                                filterChain.doFilter(request, response);
                            }else{
                                if(hasPermission(auth, request)){
                                    request.setAttribute("authSession", auth);
                                    filterChain.doFilter(request, response);
                                }else{
                                    handlerExceptionResolver.resolveException(request, response, null, new FinVerusAccessDeniedException("Permission Denied"));
                                }
                            }
                        }
                    }else {
                        handlerExceptionResolver.resolveException(request, response, null, new FinVerusUnAuthException("Invalid Token"));
                    }
                }catch(ExpiredJwtException e) {
                    request.getSession().invalidate();
                    handlerExceptionResolver.resolveException(request, response, null, new FinVerusUnAuthException("Token Expired"));
                }
            }else {
                handlerExceptionResolver.resolveException(request, response, null, new FinVerusUnAuthException("Invalid Token"));
            }
        }
    }

    private boolean isPreLoginRequest(String requestPath) {
        return getPreLoginEndpoints().stream().anyMatch(requestPath::contains);
    }

    private boolean isPublicAccessible(String[] urlPaths){
        for(String part : urlPaths){
            if(part.equals("public")){
                return true;
            }
        }
        return false;
    }

    private boolean hasPermission(Authentication authentication, HttpServletRequest request){
        String accessCode = request.getParameter("access-code");
        if(accessCode == null || authentication.getAuthorities() == null)
            return false;
        for(GrantedAuthority authority : authentication.getAuthorities()){
            if(authority.getAuthority().equals(accessCode)){
                return true;
            }
        }
        return false;
    }
}
