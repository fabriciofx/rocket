package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

public final class TestePeriodo {
	@Test
	public void contemDataHora() {
		final PeriodoDataHora periodo = new PeriodoDataHora(
				LocalDateTime.of(2015, 1, 1, 8, 0, 0),
				LocalDateTime.of(2015, 1, 31, 8, 0, 0));

		assertTrue(periodo.contains(LocalDateTime.of(2015, 1, 1, 8, 0, 0)));
		assertTrue(periodo.contains(LocalDateTime.of(2015, 1, 1, 8, 0, 1)));
		assertTrue(periodo.contains(LocalDateTime.of(2015, 1, 1, 10, 0, 0)));
		assertTrue(periodo.contains(LocalDateTime.of(2015, 1, 31, 7, 59, 59)));
		assertTrue(periodo.contains(LocalDateTime.of(2015, 1, 31, 8, 0, 0)));
	}

	@Test
	public void contemData() {
		final PeriodoData periodo = new PeriodoData(LocalDate.of(2015, 1, 1),
				LocalDate.of(2015, 1, 3));

		assertFalse(periodo.contains(LocalDate.of(2014, 12, 31)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 1)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 2)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 3)));
		assertFalse(periodo.contains(LocalDate.of(2015, 1, 4)));
	}

	@Test
	public void contemDataHoraComoString() {
		final PeriodoDataHora periodo = new PeriodoDataHora("10/10/2015",
				"08:00", "20/10/2015", "15:00");
		assertEquals("10/10/15 08:00 a 20/10/15 15:00", periodo.toString());
	}

	@Test
	public void contemDataComoString() {
		final PeriodoData periodo = new PeriodoData("10/10/2015",
				"20/10/2015");
		assertEquals("10/10/15 a 20/10/15", periodo.toString());
	}
}
