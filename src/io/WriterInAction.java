package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println("Hi Print Writer");
            printWriter.format("%d %o 0x%04x", 12, 8, 248);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
