package com.github.fabriciofx.rocket.restricao;

public final class RestPadrao<T> implements Restricao<T> {
	private final transient RestNaoVazia<T> origem;
	private final transient String regex;

	public RestPadrao(final RestNaoVazia<T> origem, final String regex) {
		this.origem = origem;
		this.regex = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(regex);
	}

	@Override
	public T valido(final T objeto) {
		origem.valido(objeto);
		if (!objeto.toString().matches(regex)) {
			throw new IllegalArgumentException(
					"não casa com a expressão regular: " + objeto.toString());
		}
		return objeto;
	}
}
