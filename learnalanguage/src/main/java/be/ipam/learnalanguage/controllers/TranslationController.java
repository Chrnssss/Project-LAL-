package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.TranslationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/translations")
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("translations", translationService.getAll());
        return "translations";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("translation", translationService.getById(id));
        return "translation-details";
    }
}

