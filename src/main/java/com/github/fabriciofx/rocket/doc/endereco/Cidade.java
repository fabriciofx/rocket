package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Cidade implements Printer {
	private final String nome;
	private final Estado estado;

	public Cidade(final String nome) {
		this(cidade(nome), estado(nome));
	}

	public Cidade(final String nome, final Estado estado) {
		this.nome = nome;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", nome, estado);
	}

	@Override
	public Media print(final Media media) {
		return media.with("cidade", nome).with("estado",
				estado.toString());
	}

	private static String cidade(final String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException(
					String.format("nome de cidade \'%s\' inválido", nome));
		}
		final String[] partes = nome.split("-");
		if (partes.length < 1) {
			throw new IllegalArgumentException(
					String.format("nome de cidade \'%s\' inválido", nome));
		}
		return partes[0];
	}

	private static Estado estado(final String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException(
					String.format("nome de cidade \'%s\' inválido", nome));
		}
		final String[] partes = nome.split("-");
		if (partes.length < 2) {
			throw new IllegalArgumentException(
					String.format("nome de cidade \'%s\' inválido", nome));
		}
		return Estado.valueOf(partes[1]);
	}
}
