package ru.gigastack.webcard.api.dto;

import lombok.Builder;

@Builder
public record UpdatePersonalInfoDTO (
        String id,
        String name,
        String title,
        String bio,
        String phone,
        String email,
        String location
){
}
