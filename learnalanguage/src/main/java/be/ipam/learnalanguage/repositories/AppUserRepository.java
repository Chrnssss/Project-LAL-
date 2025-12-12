package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}

