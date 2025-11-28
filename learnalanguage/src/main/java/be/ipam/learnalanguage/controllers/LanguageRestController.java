package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.LanguageDto;
import be.ipam.learnalanguage.mappers.LanguageMapper;
import be.ipam.learnalanguage.models.Language;
import be.ipam.learnalanguage.services.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@Tag(name = "Languages", description = "Endpoints CRUD pour la gestion des langues")
public class LanguageRestController {

    private final LanguageService languageService;
    private final LanguageMapper languageMapper;

    public LanguageRestController(LanguageService languageService,
                                  LanguageMapper languageMapper) {
        this.languageService = languageService;
        this.languageMapper = languageMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les langues",
            description = "Retourne la liste complète des langues disponibles dans le système.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des langues renvoyée avec succès")
    })
    public List<LanguageDto> findAll() {
        List<Language> languages = languageService.getAllLanguages();
        return languageMapper.toDtoList(languages);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une langue par son id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Langue trouvée"),
            @ApiResponse(responseCode = "404", description = "Langue introuvable")
    })
    public LanguageDto findById(@PathVariable Long id) {
        Language language = languageService.getLanguageByID(id);
        if (language == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
        }
        return languageMapper.toDto(language);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée une nouvelle langue")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Langue créée avec succès")
    })
    public LanguageDto create(@RequestBody LanguageDto dto) {
        Language entity = languageMapper.toEntity(dto);
        Language saved = languageService.save(entity);
        return languageMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour une langue existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Langue mise à jour"),
            @ApiResponse(responseCode = "404", description = "Langue introuvable")
    })
    public LanguageDto update(@PathVariable Long id, @RequestBody LanguageDto dto) {
        Language existing = languageService.getLanguageByID(id);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
        }

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());

        Language saved = languageService.save(existing);
        return languageMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une langue")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Langue supprimée"),
            @ApiResponse(responseCode = "404", description = "Langue introuvable")
    })
    public void delete(@PathVariable Long id) {
        if (!languageService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
        }
        languageService.deleteById(id);
    }
}

