package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;

public final class Bairro implements Printer {
	private final String nome;

	public Bairro(final String nome) {
		this.nome = new NotEmpty<String>(new NotNull<>()).valid(nome);
	}

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public Media print(Media media) {
		return media.with("bairro", nome);
	}
}
