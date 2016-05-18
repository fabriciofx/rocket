package com.github.fabriciofx.rocket.infra.bd.h2;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.bd.Conexao;
import com.github.fabriciofx.rocket.infra.bd.H2;
import com.github.fabriciofx.rocket.infra.bd.H2.Modo;
import com.github.fabriciofx.rocket.infra.bd.Insert;
import com.github.fabriciofx.rocket.infra.bd.Sgbd;
import com.github.fabriciofx.rocket.infra.bd.Update;
import com.github.fabriciofx.rocket.infra.bd.Usuario;

public final class TesteH2 {
	private final static transient String NOME_BD = "testebd";
	private Conexao conexao;

	// @After
	// public void finaliza() {
	// try {
	// new Update("DROP TABLE IF EXISTS log").execute(conexao);
	// conexao.fecha();
	// } catch (final IOException e) {
	// e.printStackTrace();
	// }
	// }

	@Test
	public void embedded() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Sgbd h2 = new H2(NOME_BD);
		assertEquals(
				String.format("jdbc:h2:%s%s%s", path, File.separator, NOME_BD),
				h2.url());
	}

	@Test
	public void memory() {
		final Sgbd h2 = new H2(NOME_BD, Modo.MEMORY);
		assertEquals(String.format("jdbc:h2:mem:", NOME_BD), h2.url());
	}

	@Test
	public void tcp() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Sgbd h2 = new H2(NOME_BD, Modo.TCP);
		assertEquals(String.format("jdbc:h2:tcp://localhost:9092//%s/%s",
				path.toString(), NOME_BD), h2.url());
	}

	@Test
	public void servidor() throws IOException, InterruptedException {
		final H2Server server = new H2Server();
		server.start();

		final Sgbd h2 = new H2(NOME_BD, Modo.TCP);
		conexao = new Conexao(h2, new Usuario("sa", ""));

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
		server.stop();
	}
}
