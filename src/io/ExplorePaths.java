package io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ExplorePaths {

    public static void main(String[] args) {
        Path parent = Paths.get("src/data");
        Path child = Paths.get("io/gun.java");

        var relativePath = parent.resolve(child);
        System.out.println(relativePath);

        Path sibling = Paths.get("pistol.java");
        System.out.println(child.resolveSibling(sibling));

        Path randomPath = Paths.get("src/data/uo/temp/Main.java");
        System.out.println(parent.relativize(randomPath));
    }
}
