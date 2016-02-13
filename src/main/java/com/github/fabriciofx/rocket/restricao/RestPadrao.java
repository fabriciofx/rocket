package com.github.fabriciofx.rocket.restricao;

public final class RestPadrao<T> extends Restricao<T> {
	public RestPadrao(final RestNaoNulo<T> restricao, final String regEx) {
		super(valida(restricao.objeto(), regEx));
	}

	private static <T> T valida(final T objeto, final String regEx) {
		if (!objeto.toString().matches(regEx)) {
			throw new IllegalArgumentException(
					"não casa com a expressão regular");
		}

		return objeto;
	}
}
