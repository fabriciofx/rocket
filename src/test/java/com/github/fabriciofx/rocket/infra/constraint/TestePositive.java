package com.github.fabriciofx.rocket.infra.constraint;

import org.junit.Test;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

public final class TestePositive {
	@Test(expected = IllegalArgumentException.class)
	public void menorInteiro() {
		new Positive<Integer>(new NotNull<>()).valid(Integer.MIN_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void menosUm() {
		new Positive<Integer>(new NotNull<>()).valid(-1);
	}

	@Test
	public void zero() {
		new Positive<Integer>(new NotNull<>()).valid(0);
	}

	@Test
	public void um() {
		new Positive<Integer>(new NotNull<>()).valid(1);
	}

	@Test
	public void maiorInteiro() {
		new Positive<Integer>(new NotNull<>()).valid(Integer.MAX_VALUE);
	}
}
