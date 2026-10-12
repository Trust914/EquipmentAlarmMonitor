package com.trust;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class AlarmLogger {
    private final Path file;

    public AlarmLogger(String fileName) {
        this.file = Path.of(fileName);
    }

    public void log(Alarm alarm) {
        try (FileWriter writer = new FileWriter(file.toFile(), true)) { // 'true' means append instead of overwrite
            writer.write(alarm.toCsv() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Could not write to log: " + e.getMessage());
        }
    }

    public void printHistory() {
        if (!Files.exists(file)) {
            System.out.println("No alarm history yet.");
            return;
        }
        try {
            for (String line : Files.readAllLines(file)) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Could not read log: " + e.getMessage());
        }
    }

}
