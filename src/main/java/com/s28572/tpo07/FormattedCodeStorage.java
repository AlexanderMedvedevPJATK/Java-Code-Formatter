package com.s28572.tpo07;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FormattedCodeStorage {

    private int id = 1;
    private final Map<Integer, String> formattedCodeMap = new HashMap<>();

    public void addFormattedCode(int id, String formattedCode) {
        formattedCodeMap.put(id, formattedCode);
        this.id++;
    }

    public String getFormattedCode(int id) {
        return formattedCodeMap.get(id);
    }
}
