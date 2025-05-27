package ru.gigastack.webcard.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gigastack.webcard.api.dto.UpdatePersonalInfoDTO;
import ru.gigastack.webcard.api.mapper.PersonalInfoMapper;
import ru.gigastack.webcard.core.model.AppUser;
import ru.gigastack.webcard.core.model.PersonalInfo;
import ru.gigastack.webcard.core.repository.PersonalInfoRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;
    private final PersonalInfoMapper personalInfoMapper;

    public PersonalInfo updatePersonalInfo( UpdatePersonalInfoDTO dto){
        if (dto.id() == null) {
            throw new IllegalArgumentException("Поле id не может быть null");
        }

        Long id = Long.parseLong(dto.id());
        PersonalInfo personalInfo = personalInfoRepository.findById(id).
                orElseThrow(() -> new RuntimeException(
                        String.format("Персональной информации не сущетсвует с id=%d",id)
                ));

        personalInfoMapper.fromUpdateDTO(personalInfo,dto);

        return personalInfoRepository.save(personalInfo);
    }

    public PersonalInfo getPersonalInfo() {
        PersonalInfo personalInfo = personalInfoRepository.findById(1L).
                orElseThrow(() -> new RuntimeException(
                        String.format("Персональной информации не сущетсвует с id=%d",1L)
                ));

        return personalInfo;
    }
}
