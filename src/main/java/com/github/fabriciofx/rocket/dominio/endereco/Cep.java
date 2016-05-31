package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Cep implements Printer {
	private final String numero;

	public Cep(final String numero) {
		this.numero = new RestPadrao<String>(
			new RestNaoVazia<>(
				new RestNaoNulo<>()
			),
			"[0-9]{8}"
		).valido(numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cep
				&& numero.equals(Cep.class.cast(o).numero);
	}

	@Override
	public String toString() {
		return numero;
	}
	
	@Override
	public Media print(Media media) {
		return media.with("cep", numero);
	}	
}
