package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.dto.AppUserDto;
import be.ipam.learnalanguage.mappers.AppUserMapper;
import be.ipam.learnalanguage.models.AppUser;
import be.ipam.learnalanguage.services.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "CRUD des utilisateurs")
public class AppUserRestController {

    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    public AppUserRestController(AppUserService appUserService, AppUserMapper appUserMapper) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
    }

    @GetMapping
    @Operation(summary = "Récupère tous les utilisateurs")
    public List<AppUserDto> findAll() {
        return appUserMapper.toDtoList(appUserService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère un utilisateur par id")
    public AppUserDto findById(@PathVariable Long id) {
        AppUser user = appUserService.getById(id);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return appUserMapper.toDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crée un utilisateur")
    public AppUserDto create(@RequestBody AppUserDto dto) {
        AppUser entity = new AppUser();
        entity.setEmail(dto.getEmail());
        entity.setPasswordHash(dto.getPasswordHash());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setRole(dto.getRole());

        AppUser saved = appUserService.save(entity);
        return appUserMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un utilisateur")
    public AppUserDto update(@PathVariable Long id, @RequestBody AppUserDto dto) {
        AppUser existing = appUserService.getById(id);
        if (existing == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        existing.setEmail(dto.getEmail());
        existing.setPasswordHash(dto.getPasswordHash());
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setRole(dto.getRole());

        AppUser saved = appUserService.save(existing);
        return appUserMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprime un utilisateur")
    public void delete(@PathVariable Long id) {
        if (!appUserService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        appUserService.deleteById(id);
    }
}
