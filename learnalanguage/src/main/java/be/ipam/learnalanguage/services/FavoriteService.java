package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.Favorite;
import be.ipam.learnalanguage.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getAll() {
        return favoriteRepository.findAll();
    }

    public Favorite getById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }

    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public boolean existsById(Long id) {
        return favoriteRepository.existsById(id);
    }

    public void deleteById(Long id) {
        favoriteRepository.deleteById(id);
    }
}

