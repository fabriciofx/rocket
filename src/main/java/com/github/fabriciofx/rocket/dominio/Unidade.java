package com.github.fabriciofx.rocket.dominio;

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
		this.simbolo = simbolo;
	}

	@Override
	public String toString() {
		return simbolo;
	}
}
