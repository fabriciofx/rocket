package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoRegEx<T> extends Validacao<T> {
	public ValidacaoRegEx(final ValidacaoNaoNulo<T> validacao,
			final String regEx) {
		super(valida(validacao.objeto(), regEx));
	}

	private static <T> T valida(final T objeto, final String regEx) {
		if (!objeto.toString().matches(regEx)) {
			throw new IllegalArgumentException(
					"não casa com a expressão regular");
		}

		return objeto;
	}
}
