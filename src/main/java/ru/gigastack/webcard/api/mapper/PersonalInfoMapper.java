package ru.gigastack.webcard.api.mapper;

import org.springframework.stereotype.Component;
import ru.gigastack.webcard.api.dto.UpdatePersonalInfoDTO;
import ru.gigastack.webcard.core.model.PersonalInfo;

@Component
public class PersonalInfoMapper {

    public void fromUpdateDTO(PersonalInfo personalInfo, UpdatePersonalInfoDTO dto) {
        personalInfo.setName(dto.name());
        personalInfo.setTitle(dto.title());
        personalInfo.setBio(dto.bio());
        personalInfo.setPhone(dto.phone());
        personalInfo.setEmail(dto.email());
        personalInfo.setLocation(dto.location());
    }

}
