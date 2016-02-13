package com.github.fabriciofx.rocket.restricao;

import org.junit.Test;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNumPos;

public final class TesteRestNumPos {
	@Test(expected = IllegalArgumentException.class)
	public void menorInteiro() {
		new RestNumPos<Integer>(new RestNaoNulo<>(Integer.MIN_VALUE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void menosUm() {
		new RestNumPos<Integer>(new RestNaoNulo<>(-1));
	}

	@Test
	public void zero() {
		new RestNumPos<Integer>(new RestNaoNulo<>(0));
	}

	@Test
	public void um() {
		new RestNumPos<Integer>(new RestNaoNulo<>(1));
	}

	@Test
	public void maiorInteiro() {
		new RestNumPos<Integer>(new RestNaoNulo<>(Integer.MAX_VALUE));
	}
}
