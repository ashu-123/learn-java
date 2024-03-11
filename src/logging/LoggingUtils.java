package logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class LoggingUtils {

    public static void initLogManger() {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/logging/logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
