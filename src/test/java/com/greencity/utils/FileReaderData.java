package com.greencity.utils;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileReaderData {

    public Stream<String> readTextFromFileGenerator(String filePath) throws  IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Iterator<String> iterator = reader.lines().iterator();

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, 0),
                false
        ).onClose(() -> {
            try {
                reader.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
 /*

    public Stream<String> readTextFromFileGenerator(String filePath) throws IOException {
        return Files.lines(Path.of(filePath));
    }

  */
}
