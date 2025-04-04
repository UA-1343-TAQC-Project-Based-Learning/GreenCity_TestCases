package com.greencity.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;

public class FileReaderData {

    public String readTextFromFile(String filePath) {
        // Створення StringBuilder для збереження всього вмісту файлу
        StringBuilder fileContent = new StringBuilder();

        // Використання FileReader для читання файлу
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Читання кожного рядка з файлу
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());  // Додаємо рядок до StringBuilder
            }
        } catch (IOException e) {
            // Обробка помилки з більш детальним повідомленням
            System.err.println("Error reading the file: " + e.getMessage());
        }


        return fileContent.toString();
    }
}
