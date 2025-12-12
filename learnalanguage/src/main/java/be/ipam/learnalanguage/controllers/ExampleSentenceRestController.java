package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.ExampleSentenceDto;
import be.ipam.learnalanguage.mappers.ExampleSentenceMapper;
import be.ipam.learnalanguage.models.ExampleSentence;
import be.ipam.learnalanguage.models.Translation;
import be.ipam.learnalanguage.services.ExampleSentenceService;
import be.ipam.learnalanguage.services.TranslationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/examples")
@Tag(name = "ExampleSentences", description = "CRUD des phrases d'exemple")
public class ExampleSentenceRestController {

    private final ExampleSentenceService exampleSentenceService;
    private final TranslationService translationService;
    private final ExampleSentenceMapper exampleSentenceMapper;

    public ExampleSentenceRestController(ExampleSentenceService exampleSentenceService,
                                         TranslationService translationService,
                                         ExampleSentenceMapper exampleSentenceMapper) {
        this.exampleSentenceService = exampleSentenceService;
        this.translationService = translationService;
        this.exampleSentenceMapper = exampleSentenceMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les phrases d'exemple")
    public List<ExampleSentenceDto> findAll() {
        return exampleSentenceMapper.toDtoList(exampleSentenceService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une phrase d'exemple par id")
    public ExampleSentenceDto findById(@PathVariable Long id) {
        ExampleSentence e = exampleSentenceService.getById(id);
        if (e == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Example not found");
        return exampleSentenceMapper.toDto(e);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée une phrase d'exemple")
    public ExampleSentenceDto create(@RequestBody ExampleSentenceDto dto) {
        Translation translation = translationService.getById(dto.getTranslationId());
        if (translation == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid translationId");

        ExampleSentence entity = new ExampleSentence();
        entity.setTranslation(translation);
        entity.setExampleText(dto.getExampleText());
        entity.setExampleTranslation(dto.getExampleTranslation());

        ExampleSentence saved = exampleSentenceService.save(entity);
        return exampleSentenceMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour une phrase d'exemple")
    public ExampleSentenceDto update(@PathVariable Long id, @RequestBody ExampleSentenceDto dto) {
        ExampleSentence existing = exampleSentenceService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Example not found");

        if (dto.getTranslationId() != null) {
            Translation translation = translationService.getById(dto.getTranslationId());
            if (translation == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid translationId");
            existing.setTranslation(translation);
        }

        existing.setExampleText(dto.getExampleText());
        existing.setExampleTranslation(dto.getExampleTranslation());

        ExampleSentence saved = exampleSentenceService.save(existing);
        return exampleSentenceMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une phrase d'exemple")
    public void delete(@PathVariable Long id) {
        if (!exampleSentenceService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Example not found");
        }
        exampleSentenceService.deleteById(id);
    }
}

