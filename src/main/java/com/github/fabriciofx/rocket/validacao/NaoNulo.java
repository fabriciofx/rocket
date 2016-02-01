package com.github.fabriciofx.rocket.validacao;

public final class NaoNulo<T> implements Restricao<T> {
	private final Restricao<T> restricao;

	public NaoNulo() {
		this(new Restricao.Terminal<T>());
	}
	
	public NaoNulo(final Restricao<T> restricao) {
		this.restricao = restricao;
	}

	@Override
	public void valida(final T objeto) {
		if (objeto == null) {
			throw new IllegalArgumentException("nulo");
		}
		
		restricao.valida(objeto);
	}
}
