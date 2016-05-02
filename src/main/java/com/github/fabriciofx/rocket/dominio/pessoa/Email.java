package com.github.fabriciofx.rocket.dominio.pessoa;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.restricao.RestEmail;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class Email implements Elemento {
	private final transient String endereco;

	public Email(final String endereco) {
		this.endereco = new RestEmail<>(
				new RestNaoVazia<>(new RestNaoNulo<>(endereco))).objeto();
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Email
				&& endereco.equals(Email.class.cast(o).endereco);
	}

	@Override
	public int hashCode() {
		return 31 + ((endereco == null) ? 0 : endereco.hashCode());
	}

	@Override
	public String toString() {
		return endereco;
	}
}
