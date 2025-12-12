package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
