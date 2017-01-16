package com.github.fabriciofx.rocket.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
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
	public <T> T value(final Class<T> type, final String key) throws IOException {
		final Object property;
		// We need this kind of crap code because the Java API is awful.
		// A better approach could be: type.parse(String string)
		try {
			if (type.equals(Byte.class)) {
				property = Byte.parseByte(properties.getProperty(key));
			} else if (type.equals(Short.class)) {
				property = Short.parseShort(properties.getProperty(key));
			} else if (type.equals(Integer.class)) {
				property = Integer.parseInt(properties.getProperty(key));
			} else if (type.equals(Long.class)) {
				property = Long.parseLong(properties.getProperty(key));
			} else if (type.equals(String.class)) {
				property = properties.getProperty(key);
			} else if (type.equals(InetAddress.class)) {
				property = InetAddress.getByName(properties.getProperty(key));
			} else if (type.equals(URL.class)) {
				property = new URL(properties.getProperty(key));
			} else if (type.equals(URI.class)) {
				property = new URI(properties.getProperty(key));
			} else {
				throw new IllegalStateException(
					String.format("type %s is not allowed", type.getName())
				);
			}
		} catch (final Exception e) {
			throw new IOException(e);
		}
		return type.cast(property);
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
