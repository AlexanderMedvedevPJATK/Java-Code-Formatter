package com.s28572.tpo07;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FormattedCodeStorage {
    private final Map<String, byte[]> formattedCodeMap = new HashMap<>();

    public void addFormattedCode(String id, byte[] formattedCode) {
        formattedCodeMap.put(id, formattedCode);
    }

    public Optional<byte[]> getFormattedCode(String id) {
        if (!formattedCodeMap.containsKey(id)) {
            System.out.println("No formatted code with id: " + id);
            return Optional.empty();
        }
        return Optional.ofNullable(formattedCodeMap.get(id));
    }
}
