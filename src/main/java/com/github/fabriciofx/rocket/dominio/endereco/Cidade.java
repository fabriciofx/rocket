package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;

public final class Cidade implements Printer {
	private final String nome;
	private final Estado estado;

	public Cidade(final String nome) {
		this(parte(nome, 0), Estado.valueOf(parte(nome, 1)));
	}

	public Cidade(final String nome, final Estado estado) {
		this.nome = new NotEmpty<String>(new NotNull<>()).valid(nome);
		this.estado = new NotNull<Estado>().valid(estado);
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
		final String[] partes = new NotEmpty<String>(new NotNull<>())
				.valid(nome).split("-");
		if (partes.length < n) {
			throw new IllegalArgumentException("nome de cidade incompleto");
		}
		return partes[n];
	}
}
