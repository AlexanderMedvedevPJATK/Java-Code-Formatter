package com.s28572.tpo07;

import com.google.googlejavaformat.java.FormatterException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping("/error")
    public RedirectView handleError(HttpServletRequest request,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletResponse response) {

        redirectAttributes.addFlashAttribute("errorTitle", "Error");
        redirectAttributes.addFlashAttribute(
                "errorHeading", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        redirectAttributes.addFlashAttribute(
                "errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

        response.setStatus(HttpStatus.NOT_FOUND.value());

        return new RedirectView("/myError", true, false);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public RedirectView handleFormattedCodeNotFoundException(RedirectAttributes redirectAttributes,
                                                             NoSuchElementException ex,
                                                             HttpServletResponse response) {

        redirectAttributes.addFlashAttribute("errorTitle", "Item not found");
        redirectAttributes.addFlashAttribute("errorHeading", "Item not found");
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());

        response.setStatus(HttpStatus.NOT_FOUND.value());

        System.out.println("EXCEPTION HANDLED");

        return new RedirectView("/myError", true, false);
    }

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public RedirectView handleFormattedCodeIdAlreadyExistsException(RedirectAttributes redirectAttributes,
                                                                    KeyAlreadyExistsException ex,
                                                                    HttpServletResponse response) {
        redirectAttributes.addFlashAttribute("errorTitle", "ID already exists");
        redirectAttributes.addFlashAttribute("errorHeading", "Element with such id already exists");
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());

        response.setStatus(HttpStatus.CONFLICT.value());

        return new RedirectView("/myError", true, false);
    }

    @ExceptionHandler(FormatterException.class)
    public RedirectView handleFormatterException(RedirectAttributes redirectAttributes,
                                                 FormatterException ex,
                                                 HttpServletResponse response) {
        redirectAttributes.addFlashAttribute("errorTitle", "Formatting error");
        redirectAttributes.addFlashAttribute("errorHeading", "Formatting error");
        redirectAttributes.addFlashAttribute("errorMessage",
                "The source code you've entered is incorrect. Please check your syntax and try again.");

        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new RedirectView("/myError", true, false);
    }
}