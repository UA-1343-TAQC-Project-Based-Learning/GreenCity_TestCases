package com.greencity.utils;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class FileReaderData {
    public String readTextFromFile(String filePath) throws IOException {

        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
}
