package com.s28572.tpo07;

import com.google.googlejavaformat.java.FormatterException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Item not found</title>
                </head>
                <body>
                    <h1>Error occurred</h1>
                    <p>%s</p>
                    <p>%s</p>
                    <a href="/">Go back to the input form</a>
                </body>
                </html>
                """, request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE), request.getAttribute(RequestDispatcher.ERROR_MESSAGE)));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleFormattedCodeNotFoundException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Item not found</title>
                </head>
                <body>
                    <h1>Item not found</h1>
                    <p>%s</p>
                    <a href="/">Go back to the input form</a>
                </body>
                </html>
                """, ex.getMessage()));
    }

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public ResponseEntity<String> handleFormattedCodeIdAlreadyExists(KeyAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>ID already exists</title>
                </head>
                <body>
                    <h1>Element with such id already exists</h1>
                    <p>%s</p>
                    <a href="/">Go back to the input form</a>
                </body>
                </html>
                """, ex.getMessage()));
    }

    @ExceptionHandler(FormatterException.class)
    public ResponseEntity<String> handleFormatterException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Formatting error</title>
                </head>
                <body>
                    <h1>Formatting error</h1>
                    <p>The source code you've entered is incorrect. Please check your syntax and try again.</p>
                    <a href="/">Go back to the input form</a>
                </body>
                </html>
                """);
    }
}