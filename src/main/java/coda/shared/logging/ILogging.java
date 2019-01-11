package coda.shared.logging;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.stereotype.Component;

@Component("logging")
public interface ILogging {
    public void trace(String message);
    public void debug(String message);
    public void warn(String message);
    public void info(String message);
    public void error(String message);
	public void exception(Exception jsonProcessingException);
}

