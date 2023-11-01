package concurrency;

import java.io.File;

public class ComputeFileSizeSequential {

    public long getTotalFileSizeInDir(final File file) {
        long total=0;
        if(file.isFile()) total+=file.length();
        else {
            File[] children = file.listFiles();
            if(children!=null) {
                for (File child : children) {
                    total += getTotalFileSizeInDir(child);
                }
            }
        }
        return total;
    }
}
