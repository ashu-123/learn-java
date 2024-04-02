package io;

import io.model.Person;
import io.model.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WritingObjects {

    public static void main(String[] args) {

        String fileName = "src/resources/person.bin";
        String studentFileName = "src/resources/person-externalize.bin";

        File file = new File(fileName );
        File studentFile = new File(studentFileName );

        Person ashu = new Person("Ashutosh", 26);
        Person mamta = new Person("Mamta", 62);
        var people = List.of(ashu, mamta);
//        System.out.println(people.getClass().getTypeName());

        try (OutputStream outputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(ashu);
            objectOutputStream.writeObject(mamta);
            objectOutputStream.writeObject(people);
        } catch (IOException ioException) {
        }

        try {
            System.out.println(Files.size(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        var student1 = new Student("Ram", 56);
        var student2 = new Student("Shyam", 23);

        try (OutputStream outputStream = new FileOutputStream(studentFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(student1);
            objectOutputStream.writeObject(student2);
        } catch (IOException ioException) {
        }
    }
}
