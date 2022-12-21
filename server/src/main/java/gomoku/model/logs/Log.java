package gomoku.model.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class Log {
	private Logger log;

	public Log(String name) throws IOException {
		log = LoggerFactory.getLogger(name);
	}

	public void info(String data) {
			log.info(data);
	}

	public void warning(String data) {
			log.warn(data);
	}

	public void severe(String data) {
			log.error(data);
	}
}