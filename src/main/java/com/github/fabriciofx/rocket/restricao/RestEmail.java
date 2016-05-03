package com.github.fabriciofx.rocket.restricao;

public final class RestEmail<T extends CharSequence> implements Restricao<T> {
	private final static String EMAIL_REGEX =
			"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
	private final RestNaoVazia<T> origem;
	
	public RestEmail(final RestNaoVazia<T> origem) {
		this.origem = origem;
	}

	@Override
	public T valido(final T objeto) {
		origem.valido(objeto);
		if (!objeto.toString().matches(EMAIL_REGEX)) {
			throw new IllegalArgumentException("endereço de e-mail inválido");
		}
		return objeto;
	}
}
