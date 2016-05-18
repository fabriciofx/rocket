package com.github.fabriciofx.rocket.infra.bd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public final class TesteTransacao {
	private Conexao conexao;
	private final static transient String NOME_BD = "logdb";
	private final long id = new Date().getTime();
	private final String msg = "Uma mensagem de log qualquer";

	private void dados(final Conexao conexao) throws IOException {
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
				.execute(conexao);
	}

	@Rule
	public ExpectedException excecao = ExpectedException.none();

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
	public void sucesso() throws IOException {
		conexao = new Conexao(new H2(NOME_BD), new Usuario("sa", ""));
		final Transacao<Integer> transacao = new Transacao<>(conexao);
		transacao.execute(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				dados(conexao);
				return 1;
			}
		});
		final Dados dados = new Select("SELECT * FROM log").execute(conexao);
		assertEquals(dados.item(0, "id"), id);
		assertEquals(dados.item(0, "info"), msg);
		assertEquals(dados.item(1, "id"), id + 1);
		assertEquals(dados.item(1, "info"), msg + "1");
		assertEquals(dados.item(2, "id"), id + 2);
		assertEquals(dados.item(2, "info"), msg + "2");
	}

	@Test
	public void falha() {
		try {
			conexao = new Conexao(new H2(NOME_BD), new Usuario("sa", ""));
			final Transacao<Integer> transacao = new Transacao<>(conexao);
			transacao.execute(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					dados(conexao);
					throw new IOException("falha proposital da transação");
				}
			});
		} catch (final IOException e) {
			try {
				final Dados dados = new Select("SELECT * FROM log")
						.execute(conexao);
				assertEquals(0, dados.itens().size());
			} catch (final IOException e2) {
			}
		}
	}
}
