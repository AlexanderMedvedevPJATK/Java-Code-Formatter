package com.s28572.tpo07;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FormatterService {

    private final FormattedCodeStorage formattedCodeStorage;

    public FormatterService(FormattedCodeStorage formattedCodeStorage) {
        this.formattedCodeStorage = formattedCodeStorage;
    }

    public String formatSource(String sourceCode) throws FormatterException {
        return new Formatter().formatSource(sourceCode);
    }

    public void addFormattedCode(SaveFormDTO saveFormDTO) {
        int time =
                saveFormDTO.getDays() * 24 * 60 * 60
                        + saveFormDTO.getHours() * 60 * 60
                        + saveFormDTO.getMinutes() * 60
                        + saveFormDTO.getSeconds();
        formattedCodeStorage.addFormattedCode(saveFormDTO.getId(), saveFormDTO.getFormattedCode(), time);
    }

    public String getFormattedCode(String id) {
        return formattedCodeStorage
                .getFormattedCode(id)
                .orElseThrow(() -> new NoSuchElementException("No formatted code with id: " + id));
    }
}
