package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("favorites", favoriteService.getAll());
        return "favorites";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("favorite", favoriteService.getById(id));
        return "favorite-details";
    }
}

