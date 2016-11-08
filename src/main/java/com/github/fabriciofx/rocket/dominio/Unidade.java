package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public enum Unidade {
	MILIGRAMA("mg"),
	GRAMA("g"),
	QUILO("kg"),
	TONELADA("T"),
	MILIMETRO("mm"),
	CENTIMETRO("cm"),
	METRO("m"),
	QUILOMETRO("km"),
	POLEGADA("\""),
	MILILITRO("ml"),
	LITRO("l");

	private final String simbolo;

	private Unidade(final String simbolo) {
		this.simbolo = new NotEmpty<String>(new NotNull<>())
				.valid(simbolo);
	}

	@Override
	public String toString() {
		return simbolo;
	}
}
