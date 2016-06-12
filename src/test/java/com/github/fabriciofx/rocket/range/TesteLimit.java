package com.github.fabriciofx.rocket.range;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.fabriciofx.rocket.range.Limit;

public final class TesteLimit {
	@Test
	public void limiteMaximo() {
		final Limit<Double> max = new Limit<>(2.15);

		assertTrue(!max.less(0.0));
		assertTrue(!max.less(1.345));
		assertTrue(!max.less(2.14));
		assertTrue(!max.less(2.15));
		assertTrue(!max.greater(2.16));
	}

	@Test
	public void limiteMinimo() {
		final Limit<Double> min = new Limit<>(2.15);

		assertTrue(!min.greater(2.16));
		assertTrue(!min.greater(2.15));
		assertTrue(!min.less(2.14));
	}
}
