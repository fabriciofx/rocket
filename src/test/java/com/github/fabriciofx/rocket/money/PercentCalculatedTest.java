package com.github.fabriciofx.rocket.money;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class PercentCalculatedTest {
	@Test
	public void ten() {
		final Money money = new MoneyPercentCalculated(
			new MoneySmart(100.00),
			new Percent(10.0)
		);
		assertEquals("R$ 10,00", money.toString());
	}
}
