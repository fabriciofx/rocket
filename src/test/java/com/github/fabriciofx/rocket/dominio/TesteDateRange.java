package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import com.github.fabriciofx.rocket.range.DateRange;
import com.github.fabriciofx.rocket.range.DateTimeRange;
import com.github.fabriciofx.rocket.transform.StringDate;
import com.github.fabriciofx.rocket.transform.StringDatetime;

public final class TesteDateRange {
	@Test
	public void contemDataHora() {
		final DateTimeRange range = new DateTimeRange(
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
		final DateRange periodo = new DateRange(LocalDate.of(2015, 1, 1),
				LocalDate.of(2015, 1, 3));

		assertFalse(periodo.contains(LocalDate.of(2014, 12, 31)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 1)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 2)));
		assertTrue(periodo.contains(LocalDate.of(2015, 1, 3)));
		assertFalse(periodo.contains(LocalDate.of(2015, 1, 4)));
	}

	@Test
	public void contemDataHoraComoString() {
		final DateTimeRange periodo = new DateTimeRange(
			new StringDatetime("10/10/2015 08:00").format(),
			new StringDatetime("20/10/2015 15:00").format()
		);
		assertEquals("10/10/15 08:00 to 20/10/15 15:00", periodo.toString());
	}

	@Test
	public void contemDataComoString() {
		final DateRange periodo = new DateRange(
			new StringDate("10/10/2015").format(),
			new StringDate("20/10/2015").format()
		);
		assertEquals("10/10/15 to 20/10/15", periodo.toString());
	}
}
