package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class Numero implements Printer {
	private final String numero;

	public Numero(final String numero) {
		this.numero = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(numero);
	}

	@Override
	public String toString() {
		return numero;
	}

	@Override
	public Media print(Media media) {
		return media.with("numero", numero);
	}
}
