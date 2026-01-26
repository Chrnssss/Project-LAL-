package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.ReviewLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewLogRepository extends JpaRepository<ReviewLog, Long> {

    List<ReviewLog> findByUserId(Long userId);
}

