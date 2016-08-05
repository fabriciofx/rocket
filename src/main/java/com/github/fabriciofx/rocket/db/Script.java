package com.github.fabriciofx.rocket.db;

import java.io.File;
import java.io.IOException;

public interface Script {
	void exec(final File file) throws IOException;
}
