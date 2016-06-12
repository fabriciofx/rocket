package com.github.fabriciofx.rocket.constraint;

import org.junit.Test;

import com.github.fabriciofx.rocket.constraint.NotNull;

public final class TesteNotNull {
	@Test(expected = IllegalArgumentException.class)
	public void nulo() {
		new NotNull<Object>().valid(null);
	}

	@Test
	public void valido() {
		new NotNull<Object>().valid(new Object());
	}
}
