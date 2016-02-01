package com.github.fabriciofx.rocket.validacao;

public interface Restricao<T> {
	void valida(T objeto);

	final class Terminal<T> implements Restricao<T> {
		@Override
		public void valida(final T objeto) {
		}
	}
}
