package ru.gigastack.webcard.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.gigastack.webcard.api.dto.AppUserDto;
import ru.gigastack.webcard.core.model.AppUser;
import ru.gigastack.webcard.core.model.Roles;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTTokenService {
    private final Key key;
    public JWTTokenService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String generateToken(AppUser appUser){
        Instant expirationTime =Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim("id",appUser.getId())
                .claim("username",appUser.getUsername())
                .claim("role",appUser.getRole())
                .setExpiration(expirationDate)
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();
        return compactTokenString;

    }
    public AppUserDto parseToken(String token){
        Jws<Claims> jwsClaims= Jwts.parser()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token);

        Long userId = jwsClaims.getBody()
                .get("id", Long.class);
        String username = jwsClaims.getBody()
                .get("username", String.class);
        Roles role = jwsClaims.getBody()
                .get("role", Roles.class);
        return new AppUserDto(userId,username,role);
    }
}
