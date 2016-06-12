package com.github.fabriciofx.rocket.dominio.pessoa;

import com.github.fabriciofx.rocket.constraint.Mod11;
import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.Documento;
import com.github.fabriciofx.rocket.infra.media.Media;

public final class Cpf implements Documento {
	private final transient String numero;

	public Cpf(final String numero) {
		this.numero = new Mod11<String>(
				new NotEmpty<>(new NotNull<>())).valid(numero);
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

	@Override
	public Media print(Media media) {
		return media.with("cpf", numero);
	}
}
