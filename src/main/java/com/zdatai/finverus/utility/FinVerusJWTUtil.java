package com.zdatai.finverus.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class FinVerusJWTUtil {
    private static final String SECRETE = "ZdataiCore2024";
    private static String KEY = null;
    private static final String SESSIONID = "sessionID";

    @Value("${sessiontimeout}")
    @Getter
    @Setter
    private int sessiontimeout;

    static {
        try {
            // generate 256 bit length secret key
            KEY = new String(Base64.getEncoder().encode(MessageDigest.getInstance("SHA-256").digest(SECRETE.getBytes(StandardCharsets.UTF_8))));
        }catch(NoSuchAlgorithmException e) {
            log.error("Key Encryption Failed {}",e.getMessage());
        }
    }

    public String generateToken(String userName, HttpSession session){
        Map<String, Object> claims = new HashMap<>();
        log.info("Session ID : {}",session.getId());
        claims.put(SESSIONID, session.getId());
        return generateJWT(claims, userName);
    }

    private String generateJWT(Map<String, Object> claims, String userName){
        long expiration = sessiontimeout * 1000L;
        return Jwts.builder()
                .claims(claims)
                .subject(userName)
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey(){
        byte[] bytes = KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).getPayload();
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the session ID from the token
    public String extractSessionID(String token) {
        return extractAllClaims(token).get(SESSIONID, String.class);
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validToken(String token, String user, String sessionID){
        return extractUsername(token).equals(user) && extractSessionID(token).equals(sessionID) && !isTokenExpired(token);
    }
}
