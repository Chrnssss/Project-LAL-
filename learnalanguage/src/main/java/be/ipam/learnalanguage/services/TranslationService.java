package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Translation;
import be.ipam.learnalanguage.repositories.TranslationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationService {

    private final TranslationRepository translationRepository;

    public TranslationService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    public List<Translation> getAll() {
        return translationRepository.findAll();
    }

    public Translation getById(Long id) {
        return translationRepository.findById(id).orElse(null);
    }

    public Translation save(Translation translation) {
        return translationRepository.save(translation);
    }

    public boolean existsById(Long id) {
        return translationRepository.existsById(id);
    }

    public void deleteById(Long id) {
        translationRepository.deleteById(id);
    }
}
