package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.h2.H2Database;

public final class TesteH2Database {
	@Test
	public void embedded() throws IOException {
		final String name = "testdb";
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Database h2 = new H2Database(name);
		assertEquals(
			String.format("jdbc:h2:%s%s%s", path, File.separator, name),
			h2.url().string()
		);
	}

	@Test
	public void memory() throws IOException {
		final String name = "testdb";
		final Database h2 = new H2Database(name, H2Database.Mode.MEMORY);
		assertEquals(
			String.format("jdbc:h2:mem:%s:DB_CLOSE_DELAY=-1", name),
			h2.url().string()
		);
	}

	@Test
	public void tcp() throws IOException {
		final String name = "testdb";
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Database h2 = new H2Database(name, H2Database.Mode.TCP);
		assertEquals(
			String.format("jdbc:h2:tcp://localhost:9092//%s/%s",
			path.toString(), name), h2.url().string()
		);
	}
}
