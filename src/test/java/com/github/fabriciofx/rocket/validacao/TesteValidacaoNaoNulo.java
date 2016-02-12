package com.github.fabriciofx.rocket.validacao;

import org.junit.Test;

public final class TesteValidacaoNaoNulo {
	@Test
	public void valido() {
		new ValidacaoNaoNulo<Object>().valida(new Object());
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalido() {
		new ValidacaoNaoNulo<Object>().valida(null);
	}
}
