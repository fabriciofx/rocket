package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

public final class Logradouro implements Documento {
	private final String nome;

	public Logradouro(final String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(nome);
	}

	@Override
	public Media print(final Media media) {
		return media.with("logradouro", toString());
	}
}
