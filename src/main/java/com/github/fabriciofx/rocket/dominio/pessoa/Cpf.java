package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.Serializable;

import com.github.fabriciofx.rocket.restricao.RestModulo11;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Cpf implements Serializable {
	private static final long serialVersionUID = -5209151784187126372L;
	private final transient String numero;

	public Cpf(final String numero) {
		this.numero = new RestModulo11<String>(new RestPadrao<String>(
				new RestNaoVazia<>(new RestNaoNulo<>(numero)), "^[\\d]{11}$"))
						.objeto();
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cpf
				&& numero.equals(Cpf.class.cast(o).numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public String toString() {
		return this.numero;
	}
}
