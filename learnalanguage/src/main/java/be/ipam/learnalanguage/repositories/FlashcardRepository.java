package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {


    List<Flashcard> findByListId(Long listId);

}
