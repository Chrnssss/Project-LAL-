package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Flashcard;
import be.ipam.learnalanguage.repositories.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;

    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public List<Flashcard> getAll() {
        return flashcardRepository.findAll();
    }

    public Flashcard getById(Long id) {
        return flashcardRepository.findById(id).orElse(null);
    }

    public Flashcard save(Flashcard flashcard) {
        return flashcardRepository.save(flashcard);
    }

    public boolean existsById(Long id) {
        return flashcardRepository.existsById(id);
    }

    public void deleteById(Long id) {
        flashcardRepository.deleteById(id);
    }
}
