package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

