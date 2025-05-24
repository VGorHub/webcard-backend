package ru.gigastack.webcard.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gigastack.webcard.core.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}