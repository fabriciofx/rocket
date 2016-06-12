package com.github.fabriciofx.rocket.dominio.intervalo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.fabriciofx.rocket.range.DefaultRange;
import com.github.fabriciofx.rocket.range.Limit;
import com.github.fabriciofx.rocket.range.Range;

public final class TesteIntervalo {
	@Test
	public void deNumerosReaisPositivos() {
		final Range<Double> intervalo = new DefaultRange<>(
				new Limit<>(2.15), new Limit<>(7.34));

		assertFalse(intervalo.contains(2.14));
		assertTrue(intervalo.contains(2.15));
		assertTrue(intervalo.contains(2.16));
		assertTrue(intervalo.contains(5.78));
		assertTrue(intervalo.contains(7.33));
		assertTrue(intervalo.contains(7.34));
		assertFalse(intervalo.contains(7.35));
	}

	@Test
	public void deNumerosReaisNegativos() {
		final Range<Double> intervalo = new DefaultRange<>(
				new Limit<>(-2.15), new Limit<>(0.0));

		assertFalse(intervalo.contains(-2.16));
		assertTrue(intervalo.contains(-2.15));
		assertTrue(intervalo.contains(-2.14));
		assertTrue(intervalo.contains(-1.456));
		assertTrue(intervalo.contains(-0.01));
		assertTrue(intervalo.contains(0.0));
		assertFalse(intervalo.contains(0.01));
	}
}
