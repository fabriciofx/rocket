package com.github.fabriciofx.rocket.config;

import java.io.IOException;
import java.io.InputStream;

public final class ConfigFile implements Config {
	private final PropertiesFromStream stream;

	public ConfigFile(final String filename) {
		this(ConfigFile.class.getClassLoader().getResourceAsStream(filename));
	}
	
	public ConfigFile(final InputStream stream) {
		this(new PropertiesFromStream(stream));
	}

	public ConfigFile(final PropertiesFromStream stream) {
		this.stream = stream;
	}

	@Override
	public String value(final String key) throws IOException {
		return this.stream.properties().getProperty(key);
	}

	@Override
	public void entry(final String key, final String value) throws IOException {
		this.stream.properties().setProperty(key, value);
	}

	@Override
	public boolean exists(final String key) throws IOException {
		return this.stream.properties().containsKey(key);
	}
}
