package com.s28572.tpo07;

import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FormatterController {

    private final FormatterService formatterService;

    public FormatterController(FormatterService formatterService) {
        this.formatterService = formatterService;
    }

    @GetMapping("/")
    public String index() {
        return "formattingPage";
    }

    @GetMapping("formattingError")
    public String formattingError() {
        return "formattingError";
    }

    @PostMapping("/formatCode")
    public String formatCode(@RequestParam String sourceCode,
                             RedirectAttributes redirectAttributes) throws FormatterException {

        String result = formatterService.formatSource(sourceCode);
        redirectAttributes.addFlashAttribute("formattedCode", result);
        return "redirect:/";
    }

    @PostMapping("/saveFormattedCode")
    public String saveFormattedCode(@ModelAttribute SaveFormDTO saveFormDTO) {
        formatterService.addFormattedCode(saveFormDTO);
        return "redirect:/formattedCode/" + saveFormDTO.getId();
    }

    @GetMapping("/formattedCode/{id}")
    public String getFormattedCode(@PathVariable("id") String id, Model model) {
        model.addAttribute("formattedCode", formatterService.getFormattedCode(id));
        return "savedCode";
    }
}