package com.github.fabriciofx.rocket.config;

import java.io.IOException;

public interface Config {
	String value(String key) throws IOException;
	
	void entry(String key, String value) throws IOException; 
	
	boolean exists(String key) throws IOException;
}
