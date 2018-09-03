package coda.shared.logging;

<<<<<<< HEAD
import java.util.Arrays;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component("logging")
public class Logging {
    private static Logger logger = LogManager.getLogger(Logging.class);
=======
public class Logging implements ILogging {
>>>>>>> 92b37bd6a6d5375b71b98df2ff545ee90fbbe972

    public Logging() {
        System.out.println("Logging constructor");

        logger.error("Log4j test");
    }

    @Override
    public void debug(String message) {
		System.out.println(message);
	}

	@Override
	public void error(String message) {
		System.err.println(message);
	}
}
