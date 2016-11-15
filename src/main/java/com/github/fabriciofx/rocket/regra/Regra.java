package com.github.fabriciofx.rocket.regra;


public interface Regra<T, R> {
	public boolean aplicavel(final T objeto);

	public R aplica(final T objeto);
}
