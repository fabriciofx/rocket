package com.github.fabriciofx.rocket.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigFile implements Config {
	private final Properties properties;

	public ConfigFile(final String filename) {
		this(ConfigFile.class.getClassLoader().getResourceAsStream(filename));
	}
	
	public ConfigFile(final InputStream stream) {
		this(ConfigFile.load(stream));
	}

	public ConfigFile(final Properties properties) {
		this.properties = properties;
	}

	@Override
	public String value(final String key) throws IOException {
		return properties.getProperty(key);
	}

	@Override
	public void entry(final String key, final String value) throws IOException {
		properties.setProperty(key, value);
	}

	@Override
	public boolean exists(final String key) throws IOException {
		return properties.containsKey(key);
	}
	
	// TODO: extract this method to an other class
	private static Properties load(final InputStream stream) {
		final Properties prop = new Properties();
		try {
			prop.load(stream);
			stream.close();
		} catch (final IOException e) {
			new IllegalArgumentException("invalid config file stream");
		}
		return prop;
	}
}
