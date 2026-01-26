package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.QuizAttemptDto;
import be.ipam.learnalanguage.mappers.QuizAttemptMapper;
import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.models.QuizAttempt;
import be.ipam.learnalanguage.models.VocabularyList;
import be.ipam.learnalanguage.services.AppUserService;
import be.ipam.learnalanguage.services.QuizAttemptService;
import be.ipam.learnalanguage.services.VocabularyListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "Quizzes", description = "Quiz (révision + score) : tentatives et historique")
public class QuizAttemptRestController {

    private final QuizAttemptService quizAttemptService;
    private final AppUserService appUserService;
    private final VocabularyListService vocabularyListService;
    private final QuizAttemptMapper quizAttemptMapper;

    public QuizAttemptRestController(QuizAttemptService quizAttemptService,
                                     AppUserService appUserService,
                                     VocabularyListService vocabularyListService,
                                     QuizAttemptMapper quizAttemptMapper) {
        this.quizAttemptService = quizAttemptService;
        this.appUserService = appUserService;
        this.vocabularyListService = vocabularyListService;
        this.quizAttemptMapper = quizAttemptMapper;
    }

    @GetMapping
    @Operation(summary = "Liste toutes les tentatives de quiz")
    public List<QuizAttemptDto> findAll() {
        return quizAttemptMapper.toDtoList(quizAttemptService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une tentative de quiz par id")
    public QuizAttemptDto findById(@PathVariable Long id) {
        QuizAttempt attempt = quizAttemptService.getById(id);
        if (attempt == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz attempt not found");
        return quizAttemptMapper.toDto(attempt);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Historique des quiz d'un utilisateur")
    public List<QuizAttemptDto> findByUser(@PathVariable Long userId,
                                           @RequestParam(required = false) Long listId) {
        if (listId == null) {
            return quizAttemptMapper.toDtoList(quizAttemptService.getByUserId(userId));
        }
        return quizAttemptMapper.toDtoList(quizAttemptService.getByUserIdAndListId(userId, listId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Enregistre un résultat de quiz (score calculé)")
    public QuizAttemptDto create(@RequestBody QuizAttemptDto dto) {
        AppUser user = appUserService.getById(dto.getUserId());
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");

        VocabularyList list = vocabularyListService.getById(dto.getListId());
        if (list == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid listId");

        int total = dto.getTotalQuestions();
        int correct = dto.getCorrectAnswers();
        if (total < 0 || correct < 0 || correct > total) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid totalQuestions/correctAnswers");
        }

        QuizAttempt entity = new QuizAttempt();
        entity.setUser(user);
        entity.setList(list);
        entity.setTotalQuestions(total);
        entity.setCorrectAnswers(correct);

        int score = quizAttemptService.computeScorePercent(total, correct);
        entity.setScorePercent(score);

        QuizAttempt saved = quizAttemptService.save(entity);
        return quizAttemptMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une tentative de quiz")
    public void delete(@PathVariable Long id) {
        if (!quizAttemptService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz attempt not found");
        }
        quizAttemptService.deleteById(id);
    }
}

