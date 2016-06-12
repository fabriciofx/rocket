package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;

public final class H2Server {
	private final static String DEFAULT_HOST = "localhost";
	private final static int DEFAULT_PORT = 9092;

	private final transient Server server;

	public H2Server() {
		this(DEFAULT_PORT);
	}

	public H2Server(final int port) {
		this(DEFAULT_HOST, port);
	}

	public H2Server(final String host, final int port) {
		try {
			System.setProperty("h2.bindAddress", host);
			server = Server.createTcpServer("-tcpPort",
					String.format("%d", port));
		} catch (final SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void start() throws IOException {
		try {
			server.start();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	public void stop() {
		server.stop();
	}
}
