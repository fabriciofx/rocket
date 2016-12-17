package com.github.fabriciofx.rocket.doc.endereco;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Cep implements Documento {
	private final String numero;

	public Cep(final String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return new Pattern<String>(
			new NotEmpty<>(
				new NotNull<>()
			),
			"[0-9]+"
		).valid(numero);
	}
	
	@Override
	public Media print(final Media media) {
		return media.with("cep", toString());
	}	
}
