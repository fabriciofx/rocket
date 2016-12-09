package com.github.fabriciofx.rocket.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public final class TestConfig {
	@Test
	public void readByte() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final byte pid = config.read(Byte.class, "pid");
		assertEquals(123, pid);
	}

	@Test
	public void readShort() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final short port = config.read(Short.class, "port");
		assertEquals(8080, port);
	}

	@Test
	public void readInteger() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final int port = config.read(Integer.class, "port");
		assertEquals(8080, port);
	}

	@Test
	public void readString() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final String host = config.read(String.class, "host");
		assertEquals("localhost", host);
	}
	
	@Test
	public void readIp() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final InetAddress ip = config.read(InetAddress.class, "ip");
		assertTrue(InetAddress.getByName("127.0.0.1").equals(ip));
	}
	
	@Test
	public void readUrl() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final URL url = config.read(URL.class, "url");
		assertTrue(
			new URL("http://www.pierobon.org/iis/review1.htm").equals(url)
		);
	}

	@Test
	public void readUri() throws IOException, URISyntaxException {
		final Config config = new ConfigFile("test-config.properties");
		final URI uri = config.read(URI.class, "uri");
		assertTrue(
			new URI("http://www.pierobon.org/iis/review1.htm.html#one")
				.equals(uri)
		);
	}
}
