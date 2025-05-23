package ru.gigastack.webcard.api.dto.auth;

public record LoginRequestDTO(
        String username,
        String password
) {
}
