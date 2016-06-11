package com.github.fabriciofx.rocket.dominio.intervalo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.Intervalo;
import com.github.fabriciofx.rocket.dominio.IntervaloPadrao;
import com.github.fabriciofx.rocket.dominio.Limite;

public final class TesteIntervalo {
	@Test
	public void deNumerosReaisPositivos() {
		final Intervalo<Double> intervalo = new IntervaloPadrao<>(
				new Limite<>(2.15), new Limite<>(7.34));

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
		final Intervalo<Double> intervalo = new IntervaloPadrao<>(
				new Limite<>(-2.15), new Limite<>(0.0));

		assertFalse(intervalo.contem(-2.16));
		assertTrue(intervalo.contem(-2.15));
		assertTrue(intervalo.contem(-2.14));
		assertTrue(intervalo.contem(-1.456));
		assertTrue(intervalo.contem(-0.01));
		assertTrue(intervalo.contem(0.0));
		assertFalse(intervalo.contem(0.01));
	}
}
