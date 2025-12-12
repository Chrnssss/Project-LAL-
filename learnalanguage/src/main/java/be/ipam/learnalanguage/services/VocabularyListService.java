package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.VocabularyList;
import be.ipam.learnalanguage.repositories.VocabularyListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyListService {

    private final VocabularyListRepository vocabularyListRepository;

    public VocabularyListService(VocabularyListRepository vocabularyListRepository) {
        this.vocabularyListRepository = vocabularyListRepository;
    }

    public List<VocabularyList> getAll() {
        return vocabularyListRepository.findAll();
    }

    public VocabularyList getById(Long id) {
        return vocabularyListRepository.findById(id).orElse(null);
    }

    public VocabularyList save(VocabularyList list) {
        return vocabularyListRepository.save(list);
    }

    public boolean existsById(Long id) {
        return vocabularyListRepository.existsById(id);
    }

    public void deleteById(Long id) {
        vocabularyListRepository.deleteById(id);
    }
}
