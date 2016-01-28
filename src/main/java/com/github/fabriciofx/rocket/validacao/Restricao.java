package com.github.fabriciofx.rocket.validacao;

public interface Restricao<T> {
	public void valida(T objeto) throws Exception;
}
