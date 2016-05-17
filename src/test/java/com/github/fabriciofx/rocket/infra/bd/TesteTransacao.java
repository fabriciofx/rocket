package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.bd.H2.Modo;
import com.github.fabriciofx.rocket.infra.bd.h2.H2Server;

public final class TesteTransacao {
	private final static transient String NOME_BD = "logdb";

	private int dados(final Conexao conexao) throws IOException {
		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id LONG PRIMARY KEY, info VARCHAR(255))")
						.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
				.execute(conexao);
		return 1;
	}

	@Test
	public void ok() throws IOException {
		final H2Server server = new H2Server();
		server.start();
		final Sgbd h2 = new H2();
		final Conexao conexao = new Conexao(h2, NOME_BD, new Usuario("sa", ""));
		final Transacao<Integer> transacao = new Transacao<>(conexao);
		transacao.execute(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return dados(conexao);
			}
		});
		conexao.fecha();
		server.stop();
	}
}
