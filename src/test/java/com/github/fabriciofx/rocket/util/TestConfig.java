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

public final class TestConfig {
	@Test
	public void readByte() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final byte pid = config.value(Byte.class, "pid");
		assertEquals(123, pid);
	}

	@Test
	public void readShort() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final short port = config.value(Short.class, "port");
		assertEquals(8080, port);
	}

	@Test
	public void readInteger() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final int port = config.value(Integer.class, "port");
		assertEquals(8080, port);
	}

	@Test
	public void readString() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final String host = config.value(String.class, "host");
		assertEquals("localhost", host);
	}
	
	@Test
	public void readIp() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final InetAddress ip = config.value(InetAddress.class, "ip");
		assertTrue(InetAddress.getByName("127.0.0.1").equals(ip));
	}
	
	@Test
	public void readUrl() throws IOException {
		final Config config = new ConfigFile("test-config.properties");
		final URL url = config.value(URL.class, "url");
		assertTrue(
			new URL("http://www.pierobon.org/iis/review1.htm").equals(url)
		);
	}

	@Test
	public void readUri() throws IOException, URISyntaxException {
		final Config config = new ConfigFile("test-config.properties");
		final URI uri = config.value(URI.class, "uri");
		assertTrue(
			new URI("http://www.pierobon.org/iis/review1.htm.html#one")
				.equals(uri)
		);
	}
}
