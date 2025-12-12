package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.TranslationDto;
import be.ipam.learnalanguage.mappers.TranslationMapper;
import be.ipam.learnalanguage.models.Flashcard;
import be.ipam.learnalanguage.models.Language;
import be.ipam.learnalanguage.models.Translation;
import be.ipam.learnalanguage.services.FlashcardService;
import be.ipam.learnalanguage.services.LanguageService;
import be.ipam.learnalanguage.services.TranslationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/translations")
@Tag(name = "Translations", description = "CRUD des traductions")
public class TranslationRestController {

    private final TranslationService translationService;
    private final FlashcardService flashcardService;
    private final LanguageService languageService;
    private final TranslationMapper translationMapper;

    public TranslationRestController(TranslationService translationService,
                                     FlashcardService flashcardService,
                                     LanguageService languageService,
                                     TranslationMapper translationMapper) {
        this.translationService = translationService;
        this.flashcardService = flashcardService;
        this.languageService = languageService;
        this.translationMapper = translationMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les traductions")
    public List<TranslationDto> findAll() {
        return translationMapper.toDtoList(translationService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une traduction par id")
    public TranslationDto findById(@PathVariable Long id) {
        Translation t = translationService.getById(id);
        if (t == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation not found");
        return translationMapper.toDto(t);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée une traduction")
    public TranslationDto create(@RequestBody TranslationDto dto) {
        Flashcard flashcard = flashcardService.getById(dto.getFlashcardId());
        if (flashcard == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid flashcardId");

        Language language = languageService.getLanguageByID(dto.getLanguageId());
        if (language == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid languageId");

        Translation entity = new Translation();
        entity.setFlashcard(flashcard);
        entity.setLanguage(language);
        entity.setText(dto.getText());

        Translation saved = translationService.save(entity);
        return translationMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour une traduction")
    public TranslationDto update(@PathVariable Long id, @RequestBody TranslationDto dto) {
        Translation existing = translationService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation not found");

        if (dto.getFlashcardId() != null) {
            Flashcard flashcard = flashcardService.getById(dto.getFlashcardId());
            if (flashcard == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid flashcardId");
            existing.setFlashcard(flashcard);
        }

        if (dto.getLanguageId() != null) {
            Language language = languageService.getLanguageByID(dto.getLanguageId());
            if (language == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid languageId");
            existing.setLanguage(language);
        }

        existing.setText(dto.getText());

        Translation saved = translationService.save(existing);
        return translationMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une traduction")
    public void delete(@PathVariable Long id) {
        if (!translationService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Translation not found");
        }
        translationService.deleteById(id);
    }
}

