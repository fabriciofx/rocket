package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fabriciofx.rocket.db.Conexao;
import com.github.fabriciofx.rocket.db.Dados;
import com.github.fabriciofx.rocket.db.H2;
import com.github.fabriciofx.rocket.db.Insert;
import com.github.fabriciofx.rocket.db.Select;
import com.github.fabriciofx.rocket.db.Sgbd;
import com.github.fabriciofx.rocket.db.Update;
import com.github.fabriciofx.rocket.db.Usuario;

public final class TesteSgbd {
	private final String NOME_BD = "logdb";
	private final transient Sgbd sgbd = new H2(NOME_BD);
	private Conexao conexao;

	@Before
	public void inicializa() throws IOException {
		conexao = new Conexao(sgbd, new Usuario("sa", ""));
	}

	@After
	public void finaliza() {
		try {
			new Update("DROP TABLE IF EXISTS log").execute(conexao);
			conexao.fecha();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void novo() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		assertEquals(
				String.format("jdbc:h2:%s%s%s", path, File.separator, NOME_BD),
				sgbd.url());
	}

	@Test
	public void cria() throws IOException {
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(conexao);
	}

	@Test
	public void insertUm() throws IOException {
		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(conexao);
		final Dados logs = new Select("SELECT * FROM log").execute(conexao);
		assertEquals(logs.item(0, "id"), id);
		assertEquals(logs.item(0, "info"), msg);
	}

	@Test
	public void insertTres() throws IOException {
		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
				.execute(conexao);
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
