package com.github.fabriciofx.rocket.restricao;

public final class RestNumNeg<T extends Number> extends Restricao<T> {
	public RestNumNeg(final RestNaoNulo<T> restricao) {
		super(valida(restricao.objeto()));
	}

	private static <T> T valida(final T objeto) {
		if (Number.class.cast(objeto).doubleValue() >= 0) {
			throw new IllegalArgumentException("número positivo");
		}

		return objeto;
	}
}
