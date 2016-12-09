package com.github.fabriciofx.rocket.util;

import java.io.IOException;

public interface Config {
	<T> T read(Class<T> type, String key) throws IOException;
	
	void write(String key, String value) throws IOException; 
	
	boolean exists(String key) throws IOException;
}
