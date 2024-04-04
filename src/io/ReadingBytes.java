package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class ReadingBytes {

    public static void main(String[] args) {
        String fileName = "src/resources/ints.bin";
        String fileNameZip = "src/resources/ints.bin.gz";
        Path pathName = Paths.get(fileNameZip);
        try {
            long size = Files.size(pathName);
            System.out.println(pathName + " size " + size);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> ints = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(fileNameZip);
             GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
             DataInputStream dataInputStream = new DataInputStream(gzipInputStream)) {
            while (true) {
                ints.add(dataInputStream.readInt());
            }

        } catch (EOFException eofException) {
            ints.forEach(System.out::println);
            System.out.println(ints.size());
            eofException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
