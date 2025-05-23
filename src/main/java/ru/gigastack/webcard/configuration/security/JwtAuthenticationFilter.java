package ru.gigastack.webcard.configuration.security;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.gigastack.webcard.api.dto.AppUserDto;
import ru.gigastack.webcard.core.model.Roles;

import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTTokenService tokenService;

    public JwtAuthenticationFilter(JWTTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException, java.io.IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeaderIsInvalid(authorizationHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UsernamePasswordAuthenticationToken token = createToken(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean authorizationHeaderIsInvalid(String authorizationHeader) {
        return authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ");
    }

    private UsernamePasswordAuthenticationToken createToken(String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        AppUserDto appUserDto = tokenService.parseToken(token);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (appUserDto.role() == Roles.admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new UsernamePasswordAuthenticationToken(appUserDto, null, authorities);
    }
}
