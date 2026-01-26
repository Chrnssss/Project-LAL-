package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.ReviewAnswerDto;
import be.ipam.learnalanguage.dto.ReviewLogDto;
import be.ipam.learnalanguage.mappers.ReviewLogMapper;
import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.models.Flashcard;
import be.ipam.learnalanguage.models.ReviewLog;
import be.ipam.learnalanguage.services.AppUserService;
import be.ipam.learnalanguage.services.FlashcardService;
import be.ipam.learnalanguage.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/revision")
@Tag(name = "Revision", description = "Révision simple (sans SRS)")
public class RevisionRestController {

    private final ReviewService reviewService;
    private final FlashcardService flashcardService;
    private final AppUserService appUserService;
    private final ReviewLogMapper reviewLogMapper;

    public RevisionRestController(ReviewService reviewService,
                                  FlashcardService flashcardService,
                                  AppUserService appUserService,
                                  ReviewLogMapper reviewLogMapper) {
        this.reviewService = reviewService;
        this.flashcardService = flashcardService;
        this.appUserService = appUserService;
        this.reviewLogMapper = reviewLogMapper;
    }

    @GetMapping
    @Operation(summary = "Retourne un lot de flashcards à réviser sur une liste")
    public List<Flashcard> getForReview(@RequestParam Long listId,
                                        @RequestParam(defaultValue = "10") int limit,
                                        @RequestParam(defaultValue = "random") String mode) {
        if (limit <= 0) limit = 10;
        return reviewService.getFlashcardsForReview(listId, limit, mode);
    }

    @PostMapping("/answer")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Enregistre une réponse de révision (log simple)")
    public ReviewLogDto answer(@RequestBody ReviewAnswerDto dto) {
        AppUser user = appUserService.getById(dto.getUserId());
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");

        Flashcard flashcard = flashcardService.getById(dto.getFlashcardId());
        if (flashcard == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid flashcardId");

        ReviewLog log = new ReviewLog();
        log.setUser(user);
        log.setFlashcard(flashcard);
        log.setSuccess(dto.isSuccess());

        ReviewLog saved = reviewService.saveLog(log);
        return reviewLogMapper.toDto(saved);
    }

    @GetMapping("/logs/user/{userId}")
    @Operation(summary = "Récupère l'historique des réponses de révision d'un utilisateur")
    public List<ReviewLogDto> logs(@PathVariable Long userId) {
        return reviewLogMapper.toDtoList(reviewService.getLogsForUser(userId));
    }
}

