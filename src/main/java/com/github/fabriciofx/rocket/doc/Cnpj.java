package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.Mod13;
import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

public final class Cnpj implements Documento {
	private final transient String numero;

	public Cnpj(final String numero) {
		this.numero = new Mod13<String>(
			new NotEmpty<>(new NotNull<>())
		).valid(numero);
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

	@Override
	public Media print(final Media media) {
		return media.with("cnpj", numero);
	}
}
