package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Placa {
	private final transient String numero;

	public Placa(final String numero) {
		this.numero = new RestPadrao<String>(
			new RestNaoVazia<>(
				new RestNaoNulo<>()
			), "^[a-zA-Z]{3}\\d{4}$"
		).valido(numero).toUpperCase();
	}

	public String numero() {
		return numero;
	}

	@Override
	public String toString() {
		return String.format("%s-%s", numero.substring(0, 3),
				numero.substring(3, numero.length()));
	}
}
