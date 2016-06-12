package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Cep implements Printer {
	private final String numero;

	public Cep(final String numero) {
		this.numero = new Pattern<String>(
			new NotEmpty<>(
				new NotNull<>()
			),
			"[0-9]{8}"
		).valid(numero);
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
