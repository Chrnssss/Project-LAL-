package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}

