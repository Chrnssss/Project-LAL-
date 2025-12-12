package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.FavoriteDto;
import be.ipam.learnalanguage.mappers.FavoriteMapper;
import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.models.Favorite;
import be.ipam.learnalanguage.models.Flashcard;
import be.ipam.learnalanguage.services.AppUserService;
import be.ipam.learnalanguage.services.FavoriteService;
import be.ipam.learnalanguage.services.FlashcardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "Favorites", description = "CRUD des favoris utilisateur ↔ flashcard")
public class FavoriteRestController {

    private final FavoriteService favoriteService;
    private final AppUserService appUserService;
    private final FlashcardService flashcardService;
    private final FavoriteMapper favoriteMapper;

    public FavoriteRestController(FavoriteService favoriteService,
                                  AppUserService appUserService,
                                  FlashcardService flashcardService,
                                  FavoriteMapper favoriteMapper) {
        this.favoriteService = favoriteService;
        this.appUserService = appUserService;
        this.flashcardService = flashcardService;
        this.favoriteMapper = favoriteMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère tous les favoris")
    public List<FavoriteDto> findAll() {
        return favoriteMapper.toDtoList(favoriteService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère un favori par id")
    public FavoriteDto findById(@PathVariable Long id) {
        Favorite f = favoriteService.getById(id);
        if (f == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
        return favoriteMapper.toDto(f);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée un favori")
    public FavoriteDto create(@RequestBody FavoriteDto dto) {
        AppUser user = appUserService.getById(dto.getUserId());
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");

        Flashcard flashcard = flashcardService.getById(dto.getFlashcardId());
        if (flashcard == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid flashcardId");

        Favorite entity = new Favorite();
        entity.setUser(user);
        entity.setFlashcard(flashcard);

        Favorite saved = favoriteService.save(entity);
        return favoriteMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un favori")
    public FavoriteDto update(@PathVariable Long id, @RequestBody FavoriteDto dto) {
        Favorite existing = favoriteService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");

        if (dto.getUserId() != null) {
            AppUser user = appUserService.getById(dto.getUserId());
            if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
            existing.setUser(user);
        }

        if (dto.getFlashcardId() != null) {
            Flashcard flashcard = flashcardService.getById(dto.getFlashcardId());
            if (flashcard == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid flashcardId");
            existing.setFlashcard(flashcard);
        }

        Favorite saved = favoriteService.save(existing);
        return favoriteMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime un favori")
    public void delete(@PathVariable Long id) {
        if (!favoriteService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
        }
        favoriteService.deleteById(id);
    }
}

