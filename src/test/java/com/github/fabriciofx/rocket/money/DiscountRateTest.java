package com.github.fabriciofx.rocket.money;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public final class DiscountRateTest {
	@Test
	public void discount() {
		final Money money = new MoneyDiscountRate(
			new MoneySmart(100.00),
			LocalDate.now().minusYears(17)
		);
		assertEquals("R$ 10,00", money.toString());
	}

}
