package ru.gigastack.webcard.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gigastack.webcard.core.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}