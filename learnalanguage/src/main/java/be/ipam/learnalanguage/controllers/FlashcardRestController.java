package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.FlashcardDto;
import be.ipam.learnalanguage.mappers.FlashcardMapper;
import be.ipam.learnalanguage.models.Flashcard;
import be.ipam.learnalanguage.models.VocabularyList;
import be.ipam.learnalanguage.services.FlashcardService;
import be.ipam.learnalanguage.services.VocabularyListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
@Tag(name = "Flashcards", description = "CRUD des flashcards")
public class FlashcardRestController {

    private final FlashcardService flashcardService;
    private final VocabularyListService vocabularyListService;
    private final FlashcardMapper flashcardMapper;

    public FlashcardRestController(FlashcardService flashcardService,
                                   VocabularyListService vocabularyListService,
                                   FlashcardMapper flashcardMapper) {
        this.flashcardService = flashcardService;
        this.vocabularyListService = vocabularyListService;
        this.flashcardMapper = flashcardMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les flashcards")
    public List<FlashcardDto> findAll() {
        return flashcardMapper.toDtoList(flashcardService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une flashcard par id")
    public FlashcardDto findById(@PathVariable Long id) {
        Flashcard flashcard = flashcardService.getById(id);
        if (flashcard == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found");
        return flashcardMapper.toDto(flashcard);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée une flashcard")
    public FlashcardDto create(@RequestBody FlashcardDto dto) {
        VocabularyList list = vocabularyListService.getById(dto.getListId());
        if (list == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid listId");

        Flashcard entity = new Flashcard();
        entity.setList(list);
        entity.setFront(dto.getFront());

        Flashcard saved = flashcardService.save(entity);
        return flashcardMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour une flashcard")
    public FlashcardDto update(@PathVariable Long id, @RequestBody FlashcardDto dto) {
        Flashcard existing = flashcardService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found");

        if (dto.getListId() != null) {
            VocabularyList list = vocabularyListService.getById(dto.getListId());
            if (list == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid listId");
            existing.setList(list);
        }

        existing.setFront(dto.getFront());

        Flashcard saved = flashcardService.save(existing);
        return flashcardMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une flashcard")
    public void delete(@PathVariable Long id) {
        if (!flashcardService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flashcard not found");
        }
        flashcardService.deleteById(id);
    }
}
