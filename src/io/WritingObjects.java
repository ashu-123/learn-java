package io;

import io.model.Person;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WritingObjects {

    public static void main(String[] args) {

        String fileName = "src/resources/person.bin";

        File file = new File(fileName );

        Person ashu = new Person("Ashutosh", 26);
        Person mamta = new Person("Mamta", 62);

        try (OutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(ashu);
            objectOutputStream.writeObject(mamta);
        } catch (IOException ioException) {
        }

        try {
            System.out.println(Files.size(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
