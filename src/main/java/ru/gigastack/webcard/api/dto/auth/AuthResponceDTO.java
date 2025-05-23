package ru.gigastack.webcard.api.dto.auth;

import lombok.Value;
import ru.gigastack.webcard.core.model.Roles;

/**
 * DTO for {@link ru.gigastack.webcard.core.model.AppUser}
 */
@Value
public class AuthResponceDTO {
    String token;
    String username;
    Roles role;
}