package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByUserId(Long userId);

    List<QuizAttempt> findByUserIdAndListId(Long userId, Long listId);
}
