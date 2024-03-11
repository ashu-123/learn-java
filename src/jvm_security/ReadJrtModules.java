package jvm_security;

import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadJrtModules {

    public static void main(String[] args) throws Exception {
        readJrtModules();
    }

    private static void readJrtModules() throws Exception {
        try {
            Path p = Paths.get(URI.create("jrt:/"));
            System.out.println("Modules:");
            Files.list(p).forEach(System.out::println);
            System.out.println();
        } catch (FileSystemNotFoundException ex) {
            System.out.println("Could not read modules");
        }
    }
}
