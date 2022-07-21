package com.won983212;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        if (args.length != 1 && args.length != 2) {
            System.out.println("Usage: parser <schedule json file> [out file]");
            return;
        }

        Path path = Paths.get(args[0]);
        Path outPath = Paths.get(removeExtension(path.getFileName()) + ".sql");
        if (args.length == 2) {
            outPath = Paths.get(args[1]);
        }

        Parser parser = new Parser();
        parser.parse(FileIO.readAll(path));
        FileIO.writeAll(outPath, parser.getAsSQL());
        System.out.println("Complete!");
    }

    private static Path removeExtension(Path file) {
        String fileName = file.toString();
        int lastIndexDot = fileName.lastIndexOf('.');
        return Paths.get(fileName.substring(0, lastIndexDot));
    }
}