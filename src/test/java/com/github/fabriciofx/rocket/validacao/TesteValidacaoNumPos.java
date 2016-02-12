package com.github.fabriciofx.rocket.validacao;

import org.junit.Test;

public final class TesteValidacaoNumPos {
	@Test(expected = IllegalArgumentException.class)
	public void menorInteiro() {
		new ValidacaoNumPos<Integer>(new ValidacaoNaoNulo<>(Integer.MIN_VALUE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void menosUm() {
		new ValidacaoNumPos<Integer>(new ValidacaoNaoNulo<>(-1));
	}

	@Test
	public void zero() {
		new ValidacaoNumPos<Integer>(new ValidacaoNaoNulo<>(0));
	}

	@Test
	public void um() {
		new ValidacaoNumPos<Integer>(new ValidacaoNaoNulo<>(1));
	}

	@Test
	public void maiorInteiro() {
		new ValidacaoNumPos<Integer>(new ValidacaoNaoNulo<>(Integer.MAX_VALUE));
	}
}
