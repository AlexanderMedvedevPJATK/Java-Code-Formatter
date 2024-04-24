package com.s28572.tpo07;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class FormattedCodeStorage {
    private final Map<String, byte[]> formattedCodeMap;
    private final ScheduledExecutorService scheduledExecutorService;

    public FormattedCodeStorage() {
        this.formattedCodeMap = new ConcurrentHashMap<>();
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void addFormattedCode(String id, String formattedCode, int storingTime) {
        byte[] serializedFormattedCode = serializeString(formattedCode);
        formattedCodeMap.put(id, serializedFormattedCode);
        scheduledExecutorService.schedule(() -> formattedCodeMap.remove(id), storingTime, TimeUnit.SECONDS);
        System.out.println(formattedCodeMap);
    }

    public Optional<String> getFormattedCode(String id) {
        if (!formattedCodeMap.containsKey(id)) {
            System.out.println("No formatted code with id: " + id);
            return Optional.empty();
        }
        return Optional.ofNullable(deserializeString(formattedCodeMap.get(id)));
    }

    private static byte[] serializeString(String str) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(str);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String deserializeString(byte[] data) {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return (String) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
