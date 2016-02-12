package com.github.fabriciofx.rocket.validacao;

public final class StrEmail<T> implements Restricao<T> {
	private final String EMAIL_REGEX =
			"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";

	private final Restricao<T> restricao;

	public StrEmail() {
		this(new Restricao.Terminal<T>());
	}
	
	public StrEmail(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (!objeto.toString().matches(EMAIL_REGEX)) {
			throw new IllegalArgumentException("e-mail inválido");
		}

		restricao.valida(objeto);
	}
}
