package be.ipam.learnalanguage.services;

import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    public AppUser getById(Long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    public AppUser save(AppUser user) {
        return appUserRepository.save(user);
    }

    public boolean existsById(Long id) {
        return appUserRepository.existsById(id);
    }

    public void deleteById(Long id) {
        appUserRepository.deleteById(id);
    }
}

