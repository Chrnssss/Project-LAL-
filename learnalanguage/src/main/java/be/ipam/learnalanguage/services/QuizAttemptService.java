package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.QuizAttempt;
import be.ipam.learnalanguage.repositories.QuizAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;

    public QuizAttemptService(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    public List<QuizAttempt> getAll() {
        return quizAttemptRepository.findAll();
    }

    public QuizAttempt getById(Long id) {
        return quizAttemptRepository.findById(id).orElse(null);
    }

    public List<QuizAttempt> getByUserId(Long userId) {
        return quizAttemptRepository.findByUserId(userId);
    }

    public List<QuizAttempt> getByUserIdAndListId(Long userId, Long listId) {
        return quizAttemptRepository.findByUserIdAndListId(userId, listId);
    }

    public QuizAttempt save(QuizAttempt attempt) {
        return quizAttemptRepository.save(attempt);
    }

    public boolean existsById(Long id) {
        return quizAttemptRepository.existsById(id);
    }

    public void deleteById(Long id) {
        quizAttemptRepository.deleteById(id);
    }

    public int computeScorePercent(int totalQuestions, int correctAnswers) {
        if (totalQuestions <= 0) return 0;
        // arrondi à l'entier le plus proche
        return (int) Math.round((correctAnswers * 100.0) / totalQuestions);
    }
}

