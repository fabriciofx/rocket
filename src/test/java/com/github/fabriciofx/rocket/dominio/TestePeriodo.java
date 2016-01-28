package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

public class TestePeriodo {
	@Test
	public void contemDataHora() {
		final Periodo periodo = new Periodo(
				LocalDateTime.of(2015, 1, 1, 8, 0, 0),
				LocalDateTime.of(2015, 1, 2, 8, 0, 0));

		assertTrue(periodo.contem(LocalDateTime.of(2015, 1, 1, 8, 0, 0)));
		assertTrue(periodo.contem(LocalDateTime.of(2015, 1, 1, 8, 0, 1)));
		assertTrue(periodo.contem(LocalDateTime.of(2015, 1, 1, 10, 0, 0)));
		assertTrue(periodo.contem(LocalDateTime.of(2015, 1, 2, 7, 59, 59)));
		assertTrue(periodo.contem(LocalDateTime.of(2015, 1, 2, 8, 0, 0)));
	}

	@Test
	public void contemData() {
		final Periodo periodo = new Periodo(LocalDate.of(2015, 1, 1),
				LocalDate.of(2015, 1, 3));

		assertFalse(periodo.contem(LocalDate.of(2014, 12, 31)));
		assertTrue(periodo.contem(LocalDate.of(2015, 1, 1)));
		assertTrue(periodo.contem(LocalDate.of(2015, 1, 2)));
		assertTrue(periodo.contem(LocalDate.of(2015, 1, 3)));
		assertFalse(periodo.contem(LocalDate.of(2015, 1, 4)));
	}

	@Test
	public void contemDataComoString() {
		final Periodo periodo = new Periodo("10/10/2015",
				"10/10/2015");
	}
}
