package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
}
