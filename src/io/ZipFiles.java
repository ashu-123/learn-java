package io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

    public static void main(String[] args) {

        var fileName = "src/resources/archive.zip";

        try (OutputStream outputStream = new FileOutputStream(fileName);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(zipOutputStream)) {
            ZipEntry firstZipEntry = new ZipEntry("bin/");
            zipOutputStream.putNextEntry(firstZipEntry);

            ZipEntry secondZipEntry = new ZipEntry("bin/ints.bin");
            zipOutputStream.putNextEntry(secondZipEntry);

            IntStream.range(0, 1_000)
                    .forEach(i -> {
                        try {
                            dataOutputStream.writeInt(i);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            ZipEntry thirdZipEntry = new ZipEntry("text/");
            zipOutputStream.putNextEntry(thirdZipEntry);

            ZipEntry fourthZipEntry = new ZipEntry("text/file.txt");
            zipOutputStream.putNextEntry(fourthZipEntry);

            dataOutputStream.writeUTF("This is a text file!");
        }
        catch (IOException ioException) {

        }
    }
}
