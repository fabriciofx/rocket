package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoNumPos<T extends Number> extends Validacao<T> {
	public ValidacaoNumPos(final ValidacaoNaoNulo<T> validacao) {
		super(valida(validacao.objeto()));
	}

	private static <T> T valida(final T objeto) {
		if (Number.class.cast(objeto).doubleValue() < 0) {
			throw new IllegalArgumentException("número negativo");
		}

		return objeto;
	}
}
