package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.ExampleSentence;
import be.ipam.learnalanguage.repositories.ExampleSentenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleSentenceService {

    private final ExampleSentenceRepository exampleSentenceRepository;

    public ExampleSentenceService(ExampleSentenceRepository exampleSentenceRepository) {
        this.exampleSentenceRepository = exampleSentenceRepository;
    }

    public List<ExampleSentence> getAll() {
        return exampleSentenceRepository.findAll();
    }

    public ExampleSentence getById(Long id) {
        return exampleSentenceRepository.findById(id).orElse(null);
    }

    public ExampleSentence save(ExampleSentence exampleSentence) {
        return exampleSentenceRepository.save(exampleSentence);
    }

    public boolean existsById(Long id) {
        return exampleSentenceRepository.existsById(id);
    }

    public void deleteById(Long id) {
        exampleSentenceRepository.deleteById(id);
    }
}

