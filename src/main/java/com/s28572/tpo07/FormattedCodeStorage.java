package com.s28572.tpo07;

import org.springframework.stereotype.Repository;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Repository
public class FormattedCodeStorage {
    private final Map<String, FormattedCode> formattedCodeMap;
    private final ScheduledExecutorService scheduledExecutorService;
    private final String SERIALIZED_DATA_FILE = "data.ser";

    public FormattedCodeStorage() {
        this.formattedCodeMap = deserializeData();
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        checkDataExpirationDate();
    }

    public void addFormattedCode(String id, String formattedCode, int storingTime) {
        if (formattedCodeMap.putIfAbsent(id, new FormattedCode(formattedCode, storingTime)) == null) {
            scheduledExecutorService.schedule(() -> formattedCodeMap.remove(id), storingTime, TimeUnit.SECONDS);
            serializeData();
        } else {
            throw new KeyAlreadyExistsException("Formatted code with id: " + id + " already exists");
        }
    }

    public Optional<String> getFormattedCode(String id) {
        if (!formattedCodeMap.containsKey(id)) {
            System.out.println("No formatted code with id: " + id);
            return Optional.empty();
        }
        return Optional.ofNullable(formattedCodeMap.get(id).getFormattedCode());
    }

    private void serializeData() {
        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(new FileOutputStream(SERIALIZED_DATA_FILE))) {
            objectOutputStream.writeObject(formattedCodeMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, FormattedCode> deserializeData() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SERIALIZED_DATA_FILE))) {
            return (ConcurrentHashMap<String, FormattedCode>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ConcurrentHashMap<>();
        }
    }

    private void checkDataExpirationDate() {
        if (formattedCodeMap.entrySet().removeIf(entry ->
                entry.getValue().getExpirationDate().isBefore(LocalDateTime.now()))) {
            serializeData();
        }
        formattedCodeMap.forEach(
                (key, value) -> scheduledExecutorService.schedule(
                        () -> formattedCodeMap.remove(key),
                        ChronoUnit.SECONDS.between(LocalDateTime.now(), value.getExpirationDate()),
                        TimeUnit.SECONDS
                )
        );
    }
}
