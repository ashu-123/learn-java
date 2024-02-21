package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class WalkFilePattern {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("src/resources/media");

        var directoriesAndFiles = Files.walk(path)
                .collect(Collectors.groupingBy(d -> {
                    if (Files.isDirectory(d)) return "dirs";
                    return "files";
                }, Collectors.toSet()));
        System.out.println(directoriesAndFiles);
        long countOfFiles = directoriesAndFiles.get("files").size();
        long countOfDirectories = directoriesAndFiles.get("dirs").size();
        System.out.println("No. of files= " + countOfFiles);
        System.out.println("No. of directories= " + countOfDirectories);
    }
}
