package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.SubscriptionDto;
import be.ipam.learnalanguage.mappers.SubscriptionMapper;
import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.models.Subscription;
import be.ipam.learnalanguage.models.VocabularyList;
import be.ipam.learnalanguage.services.AppUserService;
import be.ipam.learnalanguage.services.SubscriptionService;
import be.ipam.learnalanguage.services.VocabularyListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@Tag(name = "Subscriptions", description = "CRUD des abonnements utilisateur ↔ liste")
public class SubscriptionRestController {

    private final SubscriptionService subscriptionService;
    private final AppUserService appUserService;
    private final VocabularyListService vocabularyListService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionRestController(SubscriptionService subscriptionService,
                                      AppUserService appUserService,
                                      VocabularyListService vocabularyListService,
                                      SubscriptionMapper subscriptionMapper) {
        this.subscriptionService = subscriptionService;
        this.appUserService = appUserService;
        this.vocabularyListService = vocabularyListService;
        this.subscriptionMapper = subscriptionMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère tous les abonnements")
    public List<SubscriptionDto> findAll() {
        return subscriptionMapper.toDtoList(subscriptionService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère un abonnement par id")
    public SubscriptionDto findById(@PathVariable Long id) {
        Subscription s = subscriptionService.getById(id);
        if (s == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
        return subscriptionMapper.toDto(s);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée un abonnement")
    public SubscriptionDto create(@RequestBody SubscriptionDto dto) {
        AppUser user = appUserService.getById(dto.getUserId());
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");

        VocabularyList list = vocabularyListService.getById(dto.getListId());
        if (list == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid listId");

        Subscription entity = new Subscription();
        entity.setUser(user);
        entity.setList(list);

        Subscription saved = subscriptionService.save(entity);
        return subscriptionMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un abonnement")
    public SubscriptionDto update(@PathVariable Long id, @RequestBody SubscriptionDto dto) {
        Subscription existing = subscriptionService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");

        if (dto.getUserId() != null) {
            AppUser user = appUserService.getById(dto.getUserId());
            if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
            existing.setUser(user);
        }

        if (dto.getListId() != null) {
            VocabularyList list = vocabularyListService.getById(dto.getListId());
            if (list == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid listId");
            existing.setList(list);
        }

        Subscription saved = subscriptionService.save(existing);
        return subscriptionMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime un abonnement")
    public void delete(@PathVariable Long id) {
        if (!subscriptionService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
        }
        subscriptionService.deleteById(id);
    }
}

