package com.github.fabriciofx.rocket.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesFromStream {
	private final InputStream stream;

	public PropertiesFromStream(final String filename) {
		this(PropertiesFromStream.class.getClassLoader()
				.getResourceAsStream(filename)
		);
	}
	
	public PropertiesFromStream(final InputStream stream) {
		this.stream = stream;
	}
	
	public Properties properties() throws IOException {
		final Properties prop = new Properties();
		prop.load(this.stream);
		this.stream.close();
		return prop;
	}
}
