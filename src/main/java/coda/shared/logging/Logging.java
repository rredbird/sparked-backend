package coda.shared.logging;

public class Logging implements ILogging {

    public Logging() {
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
