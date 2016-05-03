package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public enum Unidade implements Elemento {
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

	private final transient String simbolo;

	private Unidade(final String simbolo) {
		this.simbolo = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(simbolo);
	}

	@Override
	public String toString() {
		return simbolo;
	}
}
