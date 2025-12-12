package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.VocabularyListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/lists")
public class VocabularyListController {

    private final VocabularyListService vocabularyListService;

    public VocabularyListController(VocabularyListService vocabularyListService) {
        this.vocabularyListService = vocabularyListService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("lists", vocabularyListService.getAll());
        return "lists";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("list", vocabularyListService.getById(id));
        return "list-details";
    }
}

