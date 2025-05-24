package ru.gigastack.webcard.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gigastack.webcard.api.dto.auth.AuthResponceDTO;
import ru.gigastack.webcard.api.dto.auth.LoginRequestDTO;
import ru.gigastack.webcard.configuration.security.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;


    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public AuthResponceDTO signIn(@RequestBody @Valid LoginRequestDTO request) {
        return authenticationService.signIn(request);
    }
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public AuthResponceDTO signUp(@RequestBody @Valid LoginRequestDTO request) {
        return authenticationService.signUp(request);
    }

}