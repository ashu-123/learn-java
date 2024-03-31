package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReaderInAction {

    public static void main(String[] args) throws IOException {
        File file = new File("src/resources/bat-weasels.txt");

        System.out.println(file.getName());
        System.out.println(file.exists());

        File nope = new File("src/resources/nope.txt");
        System.out.println(nope.exists());
//        nope.createNewFile();
//        System.out.println(nope.exists());

        try(Reader reader = new FileReader(file);) {
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while(line!=null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Path path = Paths.get("src/resources/bat-weasels.txt");
        try (Stream<String> lines = Files.newBufferedReader(path).lines();) {
            lines.forEach(System.out::println);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
