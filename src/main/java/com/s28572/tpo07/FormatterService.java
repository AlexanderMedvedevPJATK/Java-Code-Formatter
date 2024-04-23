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

    public void addFormattedCode(String id, String formattedCode) {
        FormattedCode formattedCodeObj = new FormattedCode(id, formattedCode);
        byte[] serializedFormattedCode = FormattedCode.serializeObject(formattedCodeObj);
        formattedCodeStorage.addFormattedCode(id, serializedFormattedCode);
    }

    public FormattedCode getFormattedCode(String id) {
        return FormattedCode.deserializeObject(
                formattedCodeStorage.getFormattedCode(id).orElseThrow(() -> new NoSuchElementException("No formatted code with id: " + id)
        ));
    }

}
