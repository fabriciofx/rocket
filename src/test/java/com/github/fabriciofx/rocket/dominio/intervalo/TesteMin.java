package com.github.fabriciofx.rocket.dominio.intervalo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.intervalo.Min;

public class TesteMin {
	@Test
	public void valorRealPositivo() {
		final Min<Double> min = new Min<>(2.15);

		assertTrue(!min.ultrapassado(2.16));
		assertTrue(!min.ultrapassado(2.15));
		assertTrue(min.ultrapassado(2.14));
	}
}
