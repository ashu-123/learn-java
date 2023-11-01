package concurrency;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class FileSizeAndSubdirectories {

    final public long size;
    final public List<File> subdirectories;

    public FileSizeAndSubdirectories(long totalSize, List<File> subdirectories) {
        this.size = totalSize;
        this.subdirectories = Collections.unmodifiableList(subdirectories);
    }
}
