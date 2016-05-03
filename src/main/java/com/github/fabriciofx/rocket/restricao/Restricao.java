package com.github.fabriciofx.rocket.restricao;

public interface Restricao<T> {
	T valido(T objeto);
}
