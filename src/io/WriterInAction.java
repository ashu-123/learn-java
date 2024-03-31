package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriterInAction {

    public static void main(String[] args) {

        Path path = Paths.get("src/resources/some-text.txt");

        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bufferedWriter.write("Hello World");
            bufferedWriter.newLine();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
