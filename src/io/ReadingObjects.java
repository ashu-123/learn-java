package io;

import io.model.Person;

import java.io.*;
import java.util.List;

public class ReadingObjects {

    public static void main(String[] args) {

        String fileName = "src/resources/person.bin";
        File file = new File(fileName);

//        try(InputStream inputStream = new FileInputStream(file);
//            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
//            System.out.println(objectInputStream.readObject());
//            System.out.println(objectInputStream.readObject());
////            System.out.println(objectInputStream.readObject());
//        }
//        catch (IOException | ClassNotFoundException ioException) {
//            ioException.printStackTrace();
//        }

        String fileNameExternalize = "src/resources/person-externalize.bin";
        File fileExternalize = new File(fileNameExternalize);

        try(InputStream inputStream = new FileInputStream(fileExternalize);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            System.out.println(objectInputStream.readObject());
            System.out.println(objectInputStream.readObject());
        }
        catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }

        String fileNameProxy = "src/resources/person-proxy.bin";
        File fileProxy = new File(fileNameProxy);

        try(InputStream inputStream = new FileInputStream(fileProxy);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            System.out.println(objectInputStream.readObject());
            System.out.println(objectInputStream.readObject());
        }
        catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }
}
