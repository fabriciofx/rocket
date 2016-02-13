package com.github.fabriciofx.rocket.restricao;

import org.junit.Test;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class TesteRestNaoNulo {
	@Test(expected = IllegalArgumentException.class)
	public void nulo() {
		new RestNaoNulo<Object>(null);
	}

	@Test
	public void valido() {
		new RestNaoNulo<Object>(new Object());
	}
}
