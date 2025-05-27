package ru.gigastack.webcard.api.dto.auth;

import lombok.Data;
import lombok.Value;
import ru.gigastack.webcard.core.model.Roles;

/**
 * DTO for {@link ru.gigastack.webcard.core.model.AppUser}
 */
@Value
@Data
public class AuthResponceDTO {
    private String token;
    private String username;
    private Roles role;
}