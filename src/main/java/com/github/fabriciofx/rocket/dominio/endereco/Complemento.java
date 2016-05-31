package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class Complemento implements Printer {
	private final String texto;

	public Complemento(final String texto) {
		this.texto = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(texto);
	}

	@Override
	public String toString() {
		return texto;
	}

	@Override
	public Media print(Media media) {
		return media.with("complemento", texto);
	}
}
