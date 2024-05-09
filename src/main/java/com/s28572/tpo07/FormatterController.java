package com.s28572.tpo07;

import com.google.googlejavaformat.java.FormatterException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class FormatterController {

    private final FormatterService formatterService;

    public FormatterController(FormatterService formatterService) {
        this.formatterService = formatterService;
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            model.addAttribute("formattedCode", inputFlashMap.get("formattedCode"));
        } else {
            model.addAttribute("formattedCode", new FormattedResponseDTO());
        }
        return "formattingPage";
    }

    @PostMapping("/formatCode")
    public RedirectView formatCode(@RequestParam("sourceCode") String sourceCode,
                                   RedirectAttributes redirectAttributes) throws FormatterException {

        redirectAttributes.addFlashAttribute("formattedCode",
                new FormattedResponseDTO(formatterService.formatSource(sourceCode)));
        return new RedirectView("/", true, false);
    }

    @PostMapping("/saveFormattedCode")
    public RedirectView saveFormattedCode(@ModelAttribute SaveFormDTO saveFormDTO) {
        formatterService.addFormattedCode(saveFormDTO);
        return new RedirectView("formattedCode/" + saveFormDTO.getId(), true, false);
    }

    @GetMapping("/formattedCode/{id}")
    public String getFormattedCode(@PathVariable("id") String id, Model model) {
        System.out.println("CONTROLLER CALLED");
        model.addAttribute("formattedCode",
                new FormattedResponseDTO(formatterService.getFormattedCode(id)));
        System.out.println("CONTROLLER FINISHED" );
        return "savedCode";
    }

    @GetMapping("/myError")
    public String error(Model model) {
        System.out.println("ERROR CALLED");
        if (model.getAttribute("errorTitle") == null) {
            model.addAttribute("errorTitle", "All good");
            model.addAttribute("errorHeading", "Why did you come here?");
            model.addAttribute("errorMessage", "There were no errors, everything is fine! :)");
        }
        System.out.println("ERROR FINISHED");
        return "error";
    }
}