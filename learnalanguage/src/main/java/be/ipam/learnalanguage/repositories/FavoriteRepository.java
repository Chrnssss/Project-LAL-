package be.ipam.learnalanguage.repositories;

import be.ipam.learnalanguage.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}

