package com.github.fabriciofx.rocket.validacao;

public interface Validacao<T> {
	void valida(T objeto);

	final class Terminal<T> implements Validacao<T> {
		@Override
		public void valida(final T objeto) {
		}
	}
}
