package com.github.fabriciofx.rocket.dominio.endereco.doc;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.Printer;

public final class Cep implements Printer {
	private final String numero;

	public Cep(final String numero) {
		this.numero = numero;
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
	
	@Override
	public Media print(final Media media) {
		return media.with("cep", numero);
	}	
}
