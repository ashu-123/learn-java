package io;

import java.io.File;
import java.io.IOException;

public class ReaderInAction {

    public static void main(String[] args) throws IOException {
        File file = new File("src/resources/bat-weasels.txt");

        System.out.println(file.getName());
        System.out.println(file.exists());

        File nope = new File("src/resources/nope.txt");
        System.out.println(nope.exists());
//        nope.createNewFile();
//        System.out.println(nope.exists());
    }
}
