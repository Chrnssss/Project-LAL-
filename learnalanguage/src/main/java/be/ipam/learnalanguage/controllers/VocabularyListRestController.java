package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.VocabularyListDto;
import be.ipam.learnalanguage.mappers.VocabularyListMapper;
import be.ipam.learnalanguage.models.Language;
import be.ipam.learnalanguage.models.VocabularyList;
import be.ipam.learnalanguage.services.LanguageService;
import be.ipam.learnalanguage.services.VocabularyListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@Tag(name = "Lists", description = "CRUD des listes de vocabulaire")
public class VocabularyListRestController {

    private final VocabularyListService vocabularyListService;
    private final LanguageService languageService;
    private final VocabularyListMapper vocabularyListMapper;

    public VocabularyListRestController(VocabularyListService vocabularyListService,
                                        LanguageService languageService,
                                        VocabularyListMapper vocabularyListMapper) {
        this.vocabularyListService = vocabularyListService;
        this.languageService = languageService;
        this.vocabularyListMapper = vocabularyListMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les listes")
    public List<VocabularyListDto> findAll() {
        return vocabularyListMapper.toDtoList(vocabularyListService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une liste par id")
    public VocabularyListDto findById(@PathVariable Long id) {
        VocabularyList list = vocabularyListService.getById(id);
        if (list == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
        return vocabularyListMapper.toDto(list);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée une liste")
    public VocabularyListDto create(@RequestBody VocabularyListDto dto) {
        Language language = languageService.getLanguageByID(dto.getLanguageId());
        if (language == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid languageId");

        VocabularyList entity = new VocabularyList();
        entity.setLanguage(language);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPublic(dto.isPublic());

        VocabularyList saved = vocabularyListService.save(entity);
        return vocabularyListMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour une liste")
    public VocabularyListDto update(@PathVariable Long id, @RequestBody VocabularyListDto dto) {
        VocabularyList existing = vocabularyListService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");

        if (dto.getLanguageId() != null) {
            Language language = languageService.getLanguageByID(dto.getLanguageId());
            if (language == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid languageId");
            existing.setLanguage(language);
        }

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setPublic(dto.isPublic());

        VocabularyList saved = vocabularyListService.save(existing);
        return vocabularyListMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une liste")
    public void delete(@PathVariable Long id) {
        if (!vocabularyListService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found");
        }
        vocabularyListService.deleteById(id);
    }
}

