package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNaoNulo<T> extends Validacao<T> {
	public ValidacaoNaoNulo(final T objeto) {
		super(valida(objeto));
	}

	private static <T> T valida(final T objeto) {
		if (objeto == null) {
			throw new IllegalArgumentException("nulo");
		}

		return objeto;
	}
}
