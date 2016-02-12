package com.github.fabriciofx.rocket.validacao;

public final class ValidacaoEmail<T extends CharSequence>
		implements Validacao<T> {
	private final String EMAIL_REGEX =
			"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";

	public ValidacaoEmail() {
	}

	@Override
	public void valida(final T objeto) {
		try {
			new ValidacaoRegEx<>(EMAIL_REGEX).valida(objeto);
		} catch (final IllegalArgumentException e) {
			throw new IllegalArgumentException("endereço de e-mail inválido");
		}
	}
}
