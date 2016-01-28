package com.github.fabriciofx.rocket.dominio.intervalo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.intervalo.Max;

public class TesteMax {
	@Test
	public void valorRealPositivo() {
		final Max<Double> max = new Max<>(2.15);

		assertTrue(!max.ultrapassado(0.0));
		assertTrue(!max.ultrapassado(1.345));
		assertTrue(!max.ultrapassado(2.14));
		assertTrue(!max.ultrapassado(2.15));
		assertTrue(max.ultrapassado(2.16));
	}
}
