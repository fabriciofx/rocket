package com.github.fabriciofx.rocket.dominio.pessoa;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.restricao.RestModulo13;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Cnpj implements Elemento {
	private final transient String numero;

	public Cnpj(final String numero) {
		this.numero = new RestModulo13<String>(new RestPadrao<String>(
				new RestNaoVazia<>(new RestNaoNulo<>(numero)), "^[\\d]{14}$"))
						.objeto();
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cnpj
				&& numero.equals(Cnpj.class.cast(o).numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public String toString() {
		return numero;
	}
}
