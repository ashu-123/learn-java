package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.zip.GZIPOutputStream;

public class WritingBytes {

    public static void main(String[] args) {
        String pathname = "src/resources/ints.bin";
        String zipFilePath = "src/resources/ints.bin.gz";

        File file = new File(zipFilePath);

        try (OutputStream outputStream = new FileOutputStream(file);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
             DataOutputStream dataOutputStream = new DataOutputStream(gzipOutputStream);) {


            dataOutputStream.writeInt(10);
            dataOutputStream.writeDouble(10d);
            dataOutputStream.writeUTF("Helloggggggggggg");

            IntStream.range(0, 1000)
                    .forEach(d -> {
                        try {
                            dataOutputStream.writeInt(d);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println(Files.size(Paths.get(zipFilePath)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            System.out.println(Files.size(Paths.get(zipFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
