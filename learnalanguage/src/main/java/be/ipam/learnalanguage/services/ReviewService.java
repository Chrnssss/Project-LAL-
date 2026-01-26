package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Flashcard;
import be.ipam.learnalanguage.models.ReviewLog;
import be.ipam.learnalanguage.repositories.FlashcardRepository;
import be.ipam.learnalanguage.repositories.ReviewLogRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ReviewService {

    private final FlashcardRepository flashcardRepository;
    private final ReviewLogRepository reviewLogRepository;

    public ReviewService(FlashcardRepository flashcardRepository,
                         ReviewLogRepository reviewLogRepository) {
        this.flashcardRepository = flashcardRepository;
        this.reviewLogRepository = reviewLogRepository;
    }

    public List<Flashcard> getFlashcardsForReview(Long listId, int limit, String mode) {
        // on récupère tout puis on limite.

        List<Flashcard> cards = flashcardRepository.findAll().stream()
                .filter(f -> f.getList() != null && f.getList().getId().equals(listId))
                .toList();

        if ("random".equalsIgnoreCase(mode)) {
            List<Flashcard> mutable = new java.util.ArrayList<>(cards);
            Collections.shuffle(mutable, new Random());
            return mutable.stream().limit(limit).toList();
        }

        // default: ordered
        return cards.stream().limit(limit).toList();
    }

    public ReviewLog saveLog(ReviewLog log) {
        return reviewLogRepository.save(log);
    }

    public List<ReviewLog> getLogsForUser(Long userId) {
        return reviewLogRepository.findByUserId(userId);
    }
}
