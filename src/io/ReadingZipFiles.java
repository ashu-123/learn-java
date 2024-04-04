package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadingZipFiles {

    public static void main(String[] args) {

        String fileName = "src/resources/archive.zip";

        File file = new File(fileName);

        try (InputStream inputStream = new FileInputStream(file);
             ZipInputStream zipInputStream = new ZipInputStream(inputStream);
             DataInputStream dataInputStream = new DataInputStream(zipInputStream)) {

            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while(zipEntry!=null) {
                if (zipEntry.isDirectory()) {
                    System.out.println("Reading directory " + zipEntry);
                } else {
                    if (zipEntry.getName().endsWith(".bin")) {
                        System.out.println("Reading file " + zipEntry);
                        List<Integer> ints = new ArrayList<>();
                        try {
                            while (true) {
                                ints.add(dataInputStream.readInt());
                            }
                        } catch (IOException ioException) {
//                            ioException.printStackTrace();
                        }
                        System.out.println(ints.size());
                    }
                    else if(zipEntry.getName().endsWith(".txt")){
                        System.out.println("Reading file " + zipEntry);
                        var content = dataInputStream.readUTF();
                        System.out.println(content);
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        } catch (IOException ioException) {

        }
    }
}
