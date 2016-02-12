package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoEmail<T extends CharSequence> extends Validacao<T> {
	private final static String EMAIL_REGEX = 
			"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";

	public ValidacaoEmail(final ValidacaoNaoVazia<T> validacao) {
		super(valida(validacao.objeto(), EMAIL_REGEX));
	}

	private static <T> T valida(final T objeto, final String regEx) {
		if (!objeto.toString().matches(regEx)) {
			throw new IllegalArgumentException("endereço de e-mail inválido");
		}

		return objeto;
	}
}
