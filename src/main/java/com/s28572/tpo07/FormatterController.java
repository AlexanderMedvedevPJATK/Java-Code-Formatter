package com.s28572.tpo07;

import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FormatterController {

    private final FormatterService formatterService;

    public FormatterController(FormatterService formatterService) {
        this.formatterService = formatterService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("formattingError")
    public String formattingError() {
        return "formattingError";
    }

    @PostMapping("/formatCode")
    public String formatCode(@RequestParam("javaCode") String sourceCode, RedirectAttributes redirectAttributes) {
        String result;
        try {
            result = formatterService.formatSource(sourceCode);
        } catch (FormatterException e) {
            return "redirect:/formattingError";
        }

        redirectAttributes.addFlashAttribute("formattedCode", result);
        return "redirect:/";
    }
}