package com.github.fabriciofx.rocket.util;

import java.io.IOException;

public interface Config {
	<T> T value(Class<T> type, String key) throws IOException;
	
	void entry(String key, String value) throws IOException; 
	
	boolean exists(String key) throws IOException;
}
