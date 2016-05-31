package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class Cidade implements Printer {
	private final String nome;
	private final Estado estado;

	public Cidade(final String nome) {
		this(parte(nome, 0), Estado.valueOf(parte(nome, 1)));
	}

	public Cidade(final String nome, final Estado estado) {
		this.nome = new RestNaoVazia<String>(new RestNaoNulo<>()).valido(nome);
		this.estado = new RestNaoNulo<Estado>().valido(estado);
	}

	@Override
	public String toString() {
		return String.format("%s-%s", nome, estado);
	}

	@Override
	public Media print(Media media) {
		return media.with("cidade", toString());
	}

	private static String parte(final String nome, final int n) {
		final String[] partes = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(nome).split("-");
		if (partes.length < n) {
			throw new IllegalArgumentException("nome de cidade incompleto");
		}
		return partes[n];
	}
}
