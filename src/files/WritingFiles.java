package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WritingFiles {
    public static void main(String[] args) {
        Path path = Path.of("src/resources/debug.log");
        try(BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
            BufferedWriter writer2 = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(writer2)) {
            writer.write("Hello!");
            printWriter.printf("\ni = %d\n", 12);
            System.out.println("Done!");

        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
