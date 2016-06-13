package com.github.fabriciofx.rocket.db;

import java.io.IOException;

public interface Command {
	void execute(Connection connection) throws IOException;
}
