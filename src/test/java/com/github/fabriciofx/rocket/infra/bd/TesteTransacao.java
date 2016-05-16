package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.junit.Test;

public final class TesteTransacao {
	@Test
	public void ok() throws IOException {
		final Conexao conexao = new Conexao(new H2(), "logdb",
				new Usuario("sa", ""));
		final Transacao<Integer> transacao = new Transacao<>(conexao);
		transacao.execute(
			new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return null;
				}				
			}
		);
		conexao.fecha();
	}
}
