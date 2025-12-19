package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.NotificationDto;
import be.ipam.learnalanguage.mappers.NotificationMapper;
import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.models.Notification;
import be.ipam.learnalanguage.services.AppUserService;
import be.ipam.learnalanguage.services.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notifications", description = "CRUD des notifications utilisateur")
public class NotificationRestController {

    private final NotificationService notificationService;
    private final AppUserService appUserService;
    private final NotificationMapper notificationMapper;

    public NotificationRestController(NotificationService notificationService,
                                      AppUserService appUserService,
                                      NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.appUserService = appUserService;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère toutes les notifications")
    public List<NotificationDto> findAll() {
        return notificationMapper.toDtoList(notificationService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une notification par id")
    public NotificationDto findById(@PathVariable Long id) {
        Notification n = notificationService.getById(id);
        if (n == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found");
        return notificationMapper.toDto(n);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée une notification")
    public NotificationDto create(@RequestBody NotificationDto dto) {
        AppUser user = appUserService.getById(dto.getUserId());
        if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");

        Notification entity = new Notification();
        entity.setUser(user);
        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setRead(dto.isRead());

        Notification saved = notificationService.save(entity);
        return notificationMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour une notification")
    public NotificationDto update(@PathVariable Long id, @RequestBody NotificationDto dto) {
        Notification existing = notificationService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found");

        if (dto.getUserId() != null) {
            AppUser user = appUserService.getById(dto.getUserId());
            if (user == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
            existing.setUser(user);
        }

        existing.setTitle(dto.getTitle());
        existing.setMessage(dto.getMessage());
        existing.setRead(dto.isRead());

        Notification saved = notificationService.save(existing);
        return notificationMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime une notification")
    public void delete(@PathVariable Long id) {
        if (!notificationService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found");
        }
        notificationService.deleteById(id);
    }
}

