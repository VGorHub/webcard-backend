package ru.gigastack.webcard.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gigastack.webcard.api.dto.UpdatePersonalInfoDTO;
import ru.gigastack.webcard.core.model.PersonalInfo;
import ru.gigastack.webcard.core.service.PersonalInfoService;

@RestController
@RequestMapping("/api/personal-info")
@Tag(name = "Персональная Информация")
@RequiredArgsConstructor
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    @PutMapping
    @Operation(summary = "Обновление персональной информации")
    public ResponseEntity<UpdatePersonalInfoDTO> update(@RequestBody UpdatePersonalInfoDTO dto){
        personalInfoService.updatePersonalInfo(dto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Operation(summary = "Получение персональной информации")
    public ResponseEntity<PersonalInfo>  getPersonalInfo(){
        PersonalInfo personalInfo =  personalInfoService.getPersonalInfo();
        return ResponseEntity.ok().body(personalInfo);
    }
}
