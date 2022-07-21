package com.won983212;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class FileIO {
    public static String readAll(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            sb.append(line);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void writeAll(Path path, String data) throws IOException {
        Files.write(path, Collections.singletonList(data), StandardCharsets.UTF_8);
    }
}
