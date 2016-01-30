package com.github.fabriciofx.rocket.dominio.intervalo;

public interface Intervalo<T extends Comparable<T>> {
	boolean contem(final T valor);
	
	final class Padrao<T extends Comparable<T>> implements Intervalo<T> {
		private final Limite<T> min;
		private final Limite<T> max;

		public Padrao(final Limite<T> min, final Limite<T> max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public boolean contem(T valor) {
			return !min.maior(valor) && !max.menor(valor);
		}
	}
}
