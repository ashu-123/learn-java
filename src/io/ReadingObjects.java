package io;

import io.model.Person;

import java.io.*;
import java.util.List;

public class ReadingObjects {

    public static void main(String[] args) {

        String fileName = "src/resources/person.bin";
        File file = new File(fileName);

        try(InputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            System.out.println(objectInputStream.readObject());
            System.out.println(objectInputStream.readObject());
//            System.out.println(objectInputStream.readObject());
        }
        catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
