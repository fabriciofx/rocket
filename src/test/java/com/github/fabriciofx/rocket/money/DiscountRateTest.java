package com.github.fabriciofx.rocket.money;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public final class DiscountRateTest {
	@Test
	public void shouldDiscount10PercentUnder18() {
		final Money money = new MoneyDiscountRate(
			new MoneySmart(100.00),
			LocalDate.now().minusYears(17)
		);
		assertEquals("R$ 10,00", money.toString());
	}

	@Test
	public void shouldNotDiscountAt18() {
		final Money money = new MoneyDiscountRate(
			new MoneySmart(100.00),
			LocalDate.now().minusYears(18)
		);
		assertEquals("R$ 100,00", money.toString());
	}

	@Test
	public void shouldNotDiscountAbove18() {
		final Money money = new MoneyDiscountRate(
			new MoneySmart(100.00),
			LocalDate.now().minusYears(19)
		);
		assertEquals("R$ 100,00", money.toString());
	}
}
