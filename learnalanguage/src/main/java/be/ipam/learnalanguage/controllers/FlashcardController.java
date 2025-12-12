package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.FlashcardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/flashcards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("flashcards", flashcardService.getAll());
        return "flashcards";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("flashcard", flashcardService.getById(id));
        return "flashcard-details";
    }
}
