package com.github.fabriciofx.rocket.infra.bd.h2;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.bd.AutoCommit;
import com.github.fabriciofx.rocket.infra.bd.Conexao;
import com.github.fabriciofx.rocket.infra.bd.H2;
import com.github.fabriciofx.rocket.infra.bd.H2.Modo;
import com.github.fabriciofx.rocket.infra.bd.Insert;
import com.github.fabriciofx.rocket.infra.bd.Sgbd;
import com.github.fabriciofx.rocket.infra.bd.Update;
import com.github.fabriciofx.rocket.infra.bd.Usuario;

public final class TesteH2 {
	private final static transient String NOME_BD = "testebd";

	@Test
	public void embedded() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Sgbd h2 = new H2();
		assertEquals(String.format("jdbc:h2:%s%s%s", path,
			File.separator, NOME_BD), h2.url(NOME_BD));
	}

	@Test
	public void memory() {
		final Sgbd h2 = new H2(Modo.MEMORY);
		assertEquals(String.format("jdbc:h2:mem:", NOME_BD), h2.url(NOME_BD));
	}

	@Test
	public void tcp() {
		final Path path = Paths.get(".").toAbsolutePath().normalize();
		final Sgbd h2 = new H2(Modo.TCP);
		assertEquals(String.format("jdbc:h2:tcp://localhost:9092//%s/%s",
				path.toString(), NOME_BD), h2.url(NOME_BD));
	}

	@Test
	public void servidor() throws IOException, InterruptedException {
		final H2Server server = new H2Server();
		server.start();

		final Sgbd h2 = new H2(Modo.TCP);
		final Conexao conexao = new Conexao(h2, NOME_BD, new Usuario("sa", ""));

		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new AutoCommit(
			new Update("CREATE TABLE IF NOT EXISTS"
					+ " log(id LONG PRIMARY KEY, info VARCHAR(255))"),
			new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg),
			new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1,
					msg + "1"),
			new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2,
					msg + "2")
		).execute(conexao);
		server.stop();
	}
}
