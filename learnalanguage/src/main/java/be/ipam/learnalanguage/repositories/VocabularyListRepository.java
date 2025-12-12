package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.VocabularyList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyListRepository extends JpaRepository<VocabularyList, Long> {
}

