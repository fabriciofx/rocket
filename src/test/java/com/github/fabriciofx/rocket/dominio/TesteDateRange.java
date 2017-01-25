package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import com.github.fabriciofx.rocket.as.StringAsDate;
import com.github.fabriciofx.rocket.as.StringAsDatetime;
import com.github.fabriciofx.rocket.range.RangeDate;
import com.github.fabriciofx.rocket.range.RangeDateTime;

public final class TesteDateRange {
	@Test
	public void contemDataHora() {
		final RangeDateTime range = new RangeDateTime(
				LocalDateTime.of(2015, 1, 1, 8, 0, 0),
				LocalDateTime.of(2015, 1, 31, 8, 0, 0)
		);
		assertTrue(range.contains(LocalDateTime.of(2015, 1, 1, 8, 0, 0)));
		assertTrue(range.contains(LocalDateTime.of(2015, 1, 1, 8, 0, 1)));
		assertTrue(range.contains(LocalDateTime.of(2015, 1, 1, 10, 0, 0)));
		assertTrue(range.contains(LocalDateTime.of(2015, 1, 31, 7, 59, 59)));
		assertTrue(range.contains(LocalDateTime.of(2015, 1, 31, 8, 0, 0)));
	}

	@Test
	public void contemData() {
		final RangeDate periodo = new RangeDate(LocalDate.of(2015, 1, 1),
				LocalDate.of(2015, 1, 3));

		assertFalse(periodo.contains(LocalDate.of(2014, 12, 31)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 1)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 2)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 3)));
		assertFalse(periodo.contains(LocalDate.of(2015, 1, 4)));
	}

	@Test
	public void contemDataHoraComoString() {
		final RangeDateTime periodo = new RangeDateTime(
			new StringAsDatetime("10/10/2015 08:00"),
			new StringAsDatetime("20/10/2015 15:00")
		);
		assertEquals("10/10/15 08:00 to 20/10/15 15:00", periodo.toString());
	}

	@Test
	public void contemDataComoString() {
		final RangeDate periodo = new RangeDate(
			new StringAsDate("10/10/2015"),
			new StringAsDate("20/10/2015")
		);
		assertEquals("10/10/15 to 20/10/15", periodo.toString());
	}
}
