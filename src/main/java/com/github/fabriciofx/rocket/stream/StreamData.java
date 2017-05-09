package com.github.fabriciofx.rocket.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamData {
	int size() throws IOException;
	
	void read(InputStream stream) throws IOException;
	
	void write(OutputStream stream) throws IOException;
}
