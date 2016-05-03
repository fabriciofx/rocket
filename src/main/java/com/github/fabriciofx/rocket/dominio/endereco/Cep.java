package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Cep implements Elemento {
	private final String numero;

	public Cep(final String numero) {
		this.numero = new RestPadrao<String>(
			new RestNaoVazia<>(
				new RestNaoNulo<>()
			),
			"[0-9]{8}"
		).valido(numero);
	}

	public String numero() {
		return numero;
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cep
				&& numero.equals(Cep.class.cast(o).numero());
	}

	@Override
	public String toString() {
		return numero;
	}
}
