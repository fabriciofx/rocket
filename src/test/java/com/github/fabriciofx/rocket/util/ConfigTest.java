package com.github.fabriciofx.rocket.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import com.github.fabriciofx.rocket.config.Config;
import com.github.fabriciofx.rocket.config.ConfigFile;
import com.github.fabriciofx.rocket.string.StringAsInetAddress;
import com.github.fabriciofx.rocket.string.StringAsNumber;

public final class ConfigTest {
	@Test
	public void readByte() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final byte pid = new StringAsNumber(config.value("pid")).byteValue();
		assertEquals(123, pid);
	}

	@Test
	public void readShort() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final short port = new StringAsNumber(config.value("port")).shortValue();
		assertEquals(8080, port);
	}

	@Test
	public void readInteger() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final int port = new StringAsNumber(config.value("port")).intValue();
		assertEquals(8080, port);
	}

	@Test
	public void readString() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final String host = config.value("host");
		assertEquals("localhost", host);
	}
	
	@Test
	public void readIp() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final InetAddress ip = new StringAsInetAddress(
			config.value("ip")
		).address();
		assertTrue(InetAddress.getByName("127.0.0.1").equals(ip));
	}
	
	@Test
	public void readUrl() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final URL url = new URL(config.value("url"));
		assertTrue(
			new URL("http://www.pierobon.org/iis/review1.htm").equals(url)
		);
	}

	@Test
	public void readUri() throws IOException, URISyntaxException {
		final Config config = new ConfigFile("test-config.properties");
		final URI uri = new URI(config.value("uri"));
		assertTrue(
			new URI("http://www.pierobon.org/iis/review1.htm.html#one")
				.equals(uri)
		);
	}
}
