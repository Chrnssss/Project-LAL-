package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Notification;
import be.ipam.learnalanguage.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public Notification getById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public boolean existsById(Long id) {
        return notificationRepository.existsById(id);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}

