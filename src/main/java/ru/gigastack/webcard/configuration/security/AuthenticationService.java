package ru.gigastack.webcard.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gigastack.webcard.api.dto.auth.AuthResponceDTO;
import ru.gigastack.webcard.api.dto.auth.LoginRequestDTO;
import ru.gigastack.webcard.core.model.AppUser;
import ru.gigastack.webcard.core.model.Roles;
import ru.gigastack.webcard.core.service.AppUserService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AppUserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public AuthResponceDTO signUp(LoginRequestDTO request) {

        var user = AppUser.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(Roles.user)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new AuthResponceDTO(jwt, user.getUsername(), user.getRole());
    }
    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public AuthResponceDTO signIn(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        AppUser user = (AppUser) userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken((UserDetails) user);
        return new AuthResponceDTO(jwt, user.getUsername(), user.getRole());
    }
}
