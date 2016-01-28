package com.github.fabriciofx.rocket.validacao;

public final class Vazia implements Restricao<String> {
	@Override
	public void valida(String str) throws Exception {
		if (str.isEmpty()) {
			throw new IllegalArgumentException("não pode ser um valor vazio");
		}
	}
}
