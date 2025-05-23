package ru.gigastack.webcard.api.dto;

import ru.gigastack.webcard.core.model.Roles;

/**
 * DTO for {@link ru.gigastack.webcard.core.model.AppUser}
 */
public record AppUserDto(Long id, String username, Roles role) {
}