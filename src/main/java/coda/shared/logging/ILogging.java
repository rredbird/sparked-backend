package coda.shared.logging;

import org.springframework.stereotype.Component;

@Component("logging")
public interface ILogging {
    public void debug(String message);

    public void error(String message);
}

