package com.github.fabriciofx.rocket.validacao;

import java.util.Collections;

import org.junit.Test;

public final class TesteValidacaoNaoVazia {
	@Test(expected = IllegalArgumentException.class)
	public void stringNula() {
		new ValidacaoNaoVazia<String>(new ValidacaoNaoNulo<>(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringVazia() {
		new ValidacaoNaoVazia<String>(new ValidacaoNaoNulo<>(""));
	}

	@Test
	public void stringNaoVazia() {
		new ValidacaoNaoVazia<String>(new ValidacaoNaoNulo<>("Pedro"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayVazio() {
		Object[] a = {};
		new ValidacaoNaoVazia<Object>(new ValidacaoNaoNulo<>(a));
	}

	@Test
	public void arrayNaoVazio() {
		Object[] a = { 1 };
		new ValidacaoNaoVazia<Object>(new ValidacaoNaoNulo<>(a));
	}

	@Test(expected = IllegalArgumentException.class)
	public void enumerationVazia() {
		new ValidacaoNaoVazia<Object>(
				new ValidacaoNaoNulo<>(Collections.emptyEnumeration()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void listaVazia() {
		new ValidacaoNaoVazia<Object>(
				new ValidacaoNaoNulo<>(Collections.emptyList()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void iteratorVazio() {
		new ValidacaoNaoVazia<Object>(
				new ValidacaoNaoNulo<>(Collections.emptyIterator()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void mapaVazio() {
		new ValidacaoNaoVazia<Object>(
				new ValidacaoNaoNulo<>(Collections.emptyMap()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setVazio() {
		new ValidacaoNaoVazia<Object>(
				new ValidacaoNaoNulo<>(Collections.emptySet()));
	}
}
