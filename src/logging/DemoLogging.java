package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class DemoLogging {

    private static final Logger LOGGER = Logger.getLogger(DemoLogging.class.getName());

//    static {
//        FileHandler fileHandler = null;
//        try {
//            fileHandler = new FileHandler(DemoLogging.class.getSimpleName() + ".log");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        fileHandler.setLevel(INFO);
//        LOGGER.addHandler(fileHandler);
//    }

    public static void main(String[] args) throws IOException {

        LoggingUtils.initLogManger();

        LOGGER.log(INFO, "Initiating main()");
    }
}
