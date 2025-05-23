package ru.gigastack.webcard.configuration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import ru.gigastack.webcard.core.model.AppUser;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtils {
    @Override
    public String generateToken(AppUser appUser){
        Instant expirationTime =Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

        String compactTokenString = Jwts.builder()
                .claim("id",appUser.getId())
                .claim("name",appUser.getName())
                .claim("role",appUser.getRole())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return "Bearer "+ compactTokenString;

    }
}
