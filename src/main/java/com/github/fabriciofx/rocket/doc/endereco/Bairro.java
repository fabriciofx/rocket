package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

public final class Bairro implements Documento {
	private final String nome;

	public Bairro(final String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public Media print(final Media media) {
		return media.with("bairro", nome);
	}
}
