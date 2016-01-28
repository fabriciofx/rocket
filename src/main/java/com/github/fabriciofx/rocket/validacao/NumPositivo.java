package com.github.fabriciofx.rocket.validacao;

public final class NumPositivo implements Restricao<Number> {
	@Override
	public void valida(Number numero) throws Exception {
		final boolean positivo = (Double.doubleToLongBits(numero.doubleValue())
				& Long.MIN_VALUE) == Long.MIN_VALUE;

		if (!positivo) {
			throw new IllegalArgumentException("não é um valor positivo");
		}
	}
}
