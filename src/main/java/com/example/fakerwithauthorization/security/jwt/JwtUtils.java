package com.example.fakerwithauthorization.security.jwt;

import com.example.fakerwithauthorization.config.AppPropreties;
import com.example.fakerwithauthorization.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

   private final AppPropreties appPropreties;

    public JwtUtils(AppPropreties appPropreties) {
        this.appPropreties = appPropreties;
    }

    public String generateJwtToken(Authentication authentication) {
        System.out.println("generate token called  ");

        UserDetailsImpl userPricipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPricipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + appPropreties.getJwtExpirationMs()))
                .signWith(SignatureAlgorithm.HS512, appPropreties.getJwtSecret())
                .compact();
    }

        public String getUsernameFromJwtToken(String token) {
            return Jwts.parser()
                    .setSigningKey(appPropreties.getJwtSecret())
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        }

        public boolean validateJwtToken(String authToken) {
            try {
                Jwts.parser().setSigningKey(appPropreties.getJwtSecret()).parseClaimsJws(authToken);
                return true;
            } catch (SignatureException e) {
                logger.error("Invalid HWT signature: {}", e.getMessage());
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token {}", e.getMessage());
            } catch (ExpiredJwtException e) {
                logger.error("JWT token is expired: {}", e.getMessage());
            } catch (UnsupportedJwtException e) {
                logger.error("JWT tokrn is unsupported: {}", e.getMessage());
            } catch (IllegalArgumentException e) {
                logger.error("JWT claims string is empty: {}", e.getMessage());
            }

            return false;
        }
}
