package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Subscription;
import be.ipam.learnalanguage.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public boolean existsById(Long id) {
        return subscriptionRepository.existsById(id);
    }

    public void deleteById(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
