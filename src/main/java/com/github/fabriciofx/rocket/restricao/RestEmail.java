package com.github.fabriciofx.rocket.restricao;

public final class RestEmail<T extends CharSequence> extends Restricao<T> {
	private final static String EMAIL_REGEX =
			"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";

	public RestEmail(final RestNaoVazia<T> restricao) {
		super(valida(restricao.objeto(), EMAIL_REGEX));
	}

	private static <T> T valida(final T objeto, final String regEx) {
		if (!objeto.toString().matches(regEx)) {
			throw new IllegalArgumentException("endereço de e-mail inválido");
		}

		return objeto;
	}
}
