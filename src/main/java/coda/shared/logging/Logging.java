package coda.shared.logging;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("logging")
public class Logging {
	public void debug(String message) {
		System.out.println(message);
		return;
	}
}
