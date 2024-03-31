package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class WritingBytes {

    public static void main(String[] args) {
        String pathname = "src/resources/ints.bin";

        File file = new File(pathname);

        try(OutputStream outputStream = new FileOutputStream(file)) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeInt(10);
            dataOutputStream.writeDouble(10d);
            dataOutputStream.writeUTF("Hello");

            IntStream.range(0, 1000)
                            .forEach(d -> {
                                try {
                                    dataOutputStream.writeInt(d);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
            System.out.println(Files.size(Paths.get(pathname)));
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
