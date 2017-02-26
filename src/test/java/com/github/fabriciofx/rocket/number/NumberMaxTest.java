package com.github.fabriciofx.rocket.number;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class NumberMaxTest {
	@Test
	public void max() {
		final Number max = new NumberMax(
			new NumbersFromMem(10, 5, -1, 121)
		);
		assertEquals(121, max.intValue());
	}
}
