package coda.shared.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging implements ILogging {
	Logger logger = LoggerFactory.getLogger(Logging.class);

    public Logging() {

    }

    @Override
    public void trace(String message) {
		logger.trace(message);
	}

	@Override
    public void debug(String message) {
		logger.debug(message);
	}

	@Override
    public void info(String message) {
		logger.info(message);
	}

	@Override
    public void warn(String message) {
		logger.warn(message);
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void exception(Exception exception) {
		logger.error(exception.getMessage());
		for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
			logger.trace(stackTraceElement.toString());
		}
	}
}
