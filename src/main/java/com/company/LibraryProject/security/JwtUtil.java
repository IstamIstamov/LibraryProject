package com.company.LibraryProject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String session) {
        return Jwts.builder()
                .setSubject(session)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public boolean isValid(String token) {
        if (!parser().isSigned(token)) {
            return false;
        }
        return !StringUtils.isBlank(parser()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public <T> T getClaims(String name, String token, Class<T> type) {
        return parser()
                .parseClaimsJws(token)
                .getBody()
                .get(name, type);
    }

    private JwtParser parser() {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build();
    }


}
