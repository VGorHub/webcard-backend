package ru.gigastack.webcard.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.gigastack.webcard.api.dto.AppUserDto;
import ru.gigastack.webcard.api.dto.auth.AuthResponceDTO;
import ru.gigastack.webcard.core.model.AppUser;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppUserMapper {
    AppUser toEntity(AuthResponceDTO authResponceDTO);

    AuthResponceDTO toAuthResponceDTO(AppUser appUser);

    AppUser toEntity(AppUserDto appUserDto);

    AppUserDto toAppUserDto(AppUser appUser);

    AppUser toEntity(AppUserDto appUserDto);
}