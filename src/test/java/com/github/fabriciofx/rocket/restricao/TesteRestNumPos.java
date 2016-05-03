package com.github.fabriciofx.rocket.restricao;

import org.junit.Test;

public final class TesteRestNumPos {
	@Test(expected = IllegalArgumentException.class)
	public void menorInteiro() {
		new RestNumPos<Integer>(new RestNaoNulo<>()).valido(Integer.MIN_VALUE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void menosUm() {
		new RestNumPos<Integer>(new RestNaoNulo<>()).valido(-1);
	}

	@Test
	public void zero() {
		new RestNumPos<Integer>(new RestNaoNulo<>()).valido(0);
	}

	@Test
	public void um() {
		new RestNumPos<Integer>(new RestNaoNulo<>()).valido(1);
	}

	@Test
	public void maiorInteiro() {
		new RestNumPos<Integer>(new RestNaoNulo<>()).valido(Integer.MAX_VALUE);
	}
}
