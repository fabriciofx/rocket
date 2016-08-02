package com.github.fabriciofx.rocket.dominio.endereco.doc;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Logradouro implements Printer {
	private final String nome;

	public Logradouro(final String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public Media print(final Media media) {
		return media.with("logradouro", nome);
	}
}
