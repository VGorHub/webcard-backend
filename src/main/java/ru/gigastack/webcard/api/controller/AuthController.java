package ru.gigastack.webcard.api.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.gigastack.webcard.api.dto.auth.AuthResponceDTO;
import ru.gigastack.webcard.api.dto.auth.LoginRequestDTO;
import ru.gigastack.webcard.configuration.security.JWTTokenService;
import ru.gigastack.webcard.core.model.AppUser;

import java.net.Authenticator;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, JWTTokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public AuthResponceDTO login(@RequestBody LoginRequestDTO loginRequest){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password()));
        AppUser appUser = (AppUser) auth.getPrincipal();
        String token = tokenService.generateToken(appUser);
        return new AuthResponceDTO(token,appUser.getUsername(),appUser.getRole());
    }
    @GetMapping("/me")
    public AppUser me(@AuthenticationPrincipal AppUser appUser) {
        return appUser;
    }
}
