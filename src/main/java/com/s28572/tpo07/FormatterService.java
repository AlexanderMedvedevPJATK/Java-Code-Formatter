package com.s28572.tpo07;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Service;

@Service
public class FormatterService {

    private final FormattedCodeStorage formattedCodeStorage;

    public FormatterService(FormattedCodeStorage formattedCodeStorage) {
        this.formattedCodeStorage = formattedCodeStorage;
    }

    public String formatSource(String sourceCode) throws FormatterException {
        return new Formatter().formatSource(sourceCode);
    }

    public void addFormattedCode(int id, String formattedCode) {
        formattedCodeStorage.addFormattedCode(id, formattedCode);
    }

    public String getFormattedCode(int id) {
        return formattedCodeStorage.getFormattedCode(id);
    }

}
