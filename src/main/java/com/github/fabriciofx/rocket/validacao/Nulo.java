package com.github.fabriciofx.rocket.validacao;

public final class Nulo implements Restricao<Object> {
	@Override
	public void valida(Object objeto) throws Exception {
		if (objeto == null) {
			throw new IllegalArgumentException("não pode ser um valor nulo");
		}
	}
}
