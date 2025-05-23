package ru.gigastack.webcard.api.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gigastack.webcard.api.dto.auth.LoginRequestDTO;

public class AuthController {

    @RestController
    public class login(@RequestBody LoginRequestDTO dto)
}
