package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadingFiles {

    public static void main(String[] args) {
        Path path = Path.of("src/resources/imp_links.txt");
        System.out.println("Path exists: " + Files.exists(path));

        try(BufferedReader reader = Files.newBufferedReader(path);) {
            String line = reader.readLine();
            while(line!=null) {
                System.out.println("Line:-" + line);
                line = reader.readLine();
            }
        }
        catch (IOException ioException) {

        }
    }
}
