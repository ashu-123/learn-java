package logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.util.logging.Level.WARNING;

public class LoggingUsingSimpleFormatter {

    private static final Logger LOGGER = Logger.getLogger(LoggingUsingSimpleFormatter.class.getName());

    static {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(LoggingUsingSimpleFormatter.class.getSimpleName() + ".log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileHandler.setLevel(WARNING);
        fileHandler.setFormatter(new SimpleFormatter());
        Filter filter = s -> !s.getSourceMethodName().equals("dontLog");
        fileHandler.setFilter(filter);
        LOGGER.addHandler(fileHandler);
    }

    public static void main(String[] args) {
        LOGGER.log(WARNING, "main initiated()");
        dontLog();
    }

    private static void dontLog() {
        LOGGER.log(WARNING, "dontLog initiated()");
    }
}
