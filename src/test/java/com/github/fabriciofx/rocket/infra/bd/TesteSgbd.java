package com.github.fabriciofx.rocket.infra.bd;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public final class TesteSgbd {
	private final String NOME_BD = "logdb";
	private Conexao conexao;

	@Before
	public void inicializa() throws IOException {
		conexao = new Conexao(new H2(NOME_BD), new Usuario("sa", ""));
	}

	@After
	public void finaliza() {
		try {
			new AutoCommit(new Update("DROP TABLE IF EXISTS log"))
					.execute(conexao);
			conexao.fecha();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void novo() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		assertEquals(String.format("jdbc:h2:%s%s%s", path, File.separator,
				NOME_BD), conexao.url());
	}

	@Test
	public void cria() throws IOException {
		new AutoCommit(new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))"))
						.execute(conexao);
	}

	@Test
	public void insertUm() throws IOException {
		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new AutoCommit(
				new Update("CREATE TABLE IF NOT EXISTS"
						+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))"),
				new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg))
						.execute(conexao);
		final Dados logs = new Select("SELECT * FROM log").execute(conexao);
		assertEquals(logs.item(0, "id"), id);
		assertEquals(logs.item(0, "info"), msg);
	}

	@Test
	public void insertTres() throws IOException {
		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new AutoCommit(
			new Update("CREATE TABLE IF NOT EXISTS"
					+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))"),
			new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg),
			new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1,
					msg + "1"),
			new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2,
					msg + "2")
		).execute(conexao);
		final Select select = new Select("SELECT * FROM log");
		final Dados logs = select.execute(conexao);
		assertEquals(logs.item(0, "id"), id);
		assertEquals(logs.item(0, "info"), msg);
		assertEquals(logs.item(1, "id"), id + 1);
		assertEquals(logs.item(1, "info"), msg + "1");
		assertEquals(logs.item(2, "id"), id + 2);
		assertEquals(logs.item(2, "info"), msg + "2");
	}
}
