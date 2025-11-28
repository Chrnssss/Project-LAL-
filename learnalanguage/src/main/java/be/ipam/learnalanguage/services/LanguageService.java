package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Language;
import be.ipam.learnalanguage.repositories.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguageByID(Long ID) {
        return languageRepository.findById(ID).orElse(null);
    }
    
    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return languageRepository.existsById(id);
    }
}
