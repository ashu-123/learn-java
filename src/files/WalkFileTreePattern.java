package files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTreePattern {

    public static void main(String[] args) throws IOException{

        Path path = Paths.get("src/resources/media");

        var visitor = new FileVisitor<Path>() {

            private long countFiles = 0;
            private long countDirs = 0;
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                countDirs++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                countFiles++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(path, visitor);
        System.out.println("count of files = " + visitor.countFiles);
        System.out.println("count of directories = " + visitor.countDirs);
    }
}
