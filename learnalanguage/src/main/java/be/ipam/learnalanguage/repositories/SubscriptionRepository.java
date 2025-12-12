package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}

