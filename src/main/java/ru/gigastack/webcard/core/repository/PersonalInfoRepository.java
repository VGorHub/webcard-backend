package ru.gigastack.webcard.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gigastack.webcard.core.model.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo,Long> {

}
