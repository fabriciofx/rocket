package com.github.fabriciofx.rocket.validacao;

import org.junit.Test;

public final class TesteValidacaoNaoNulo {
	@Test(expected = IllegalArgumentException.class)
	public void nulo() {
		new ValidacaoNaoNulo<Object>(null);
	}

	@Test
	public void valido() {
		new ValidacaoNaoNulo<Object>(new Object());
	}
}
