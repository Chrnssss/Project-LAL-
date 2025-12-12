package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.ExampleSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleSentenceRepository extends JpaRepository<ExampleSentence, Long> {
}

