package com.github.fabriciofx.rocket.dominio.intervalo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class TesteLimite {
	@Test
	public void limiteMaximo() {
		final Limite<Double> max = new Limite<>(2.15);

		assertTrue(!max.menor(0.0));
		assertTrue(!max.menor(1.345));
		assertTrue(!max.menor(2.14));
		assertTrue(!max.menor(2.15));
		assertTrue(!max.maior(2.16));
	}

	@Test
	public void limiteMinimo() {
		final Limite<Double> min = new Limite<>(2.15);

		assertTrue(!min.maior(2.16));
		assertTrue(!min.maior(2.15));
		assertTrue(!min.menor(2.14));
	}
}
