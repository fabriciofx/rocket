package com.github.fabriciofx.rocket.dominio.intervalo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;
import com.github.fabriciofx.rocket.dominio.intervalo.Max;
import com.github.fabriciofx.rocket.dominio.intervalo.Min;

public class TesteIntervalo {
	@Test
	public void deNumerosReaisPositivos() {
		Intervalo<Double> intervalo = new Intervalo<>(new Min<>(2.15),
				new Max<>(7.34));

		assertFalse(intervalo.contem(2.14));
		assertTrue(intervalo.contem(2.15));
		assertTrue(intervalo.contem(2.16));
		assertTrue(intervalo.contem(5.78));
		assertTrue(intervalo.contem(7.33));
		assertTrue(intervalo.contem(7.34));
		assertFalse(intervalo.contem(7.35));
	}

	@Test
	public void deNumerosReaisNegativos() {
		Intervalo<Double> intervalo = new Intervalo<>(new Min<>(-2.15),
				new Max<>(0.0));

		assertFalse(intervalo.contem(-2.16));
		assertTrue(intervalo.contem(-2.15));
		assertTrue(intervalo.contem(-2.14));
		assertTrue(intervalo.contem(-1.456));
		assertTrue(intervalo.contem(-0.01));
		assertTrue(intervalo.contem(0.0));
		assertFalse(intervalo.contem(0.01));
	}
}
