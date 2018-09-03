package coda.shared.logging;

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

    public Logging() {
        System.out.println("Logging constructor");

        logger.error("Log4j test");
    }

    public void debug(String message) {
		System.out.println(message);
		return;
	}
}
