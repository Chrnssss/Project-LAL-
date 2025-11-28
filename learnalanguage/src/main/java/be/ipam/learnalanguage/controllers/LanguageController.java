package be.ipam.learnalanguage.controllers;

import be.ipam.learnalanguage.services.LanguageService;
import be.ipam.learnalanguage.models.Language;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public String listLanguages(Model model) {
        model.addAttribute("languages", languageService.getAllLanguages());
        return "languages"; // => templates/languages.html
    }

    @GetMapping("/{id}")
    public String viewLanguage(@PathVariable Long id, Model model) {
        Language language = languageService.getLanguageByID(id);
        model.addAttribute("language", language);
        return "language-details"; // => templates/language-details.html
    }
}

