package com.github.fabriciofx.rocket.restricao;

import java.util.Collections;

import org.junit.Test;

public final class TesteRestNaoVazia {
	@Test(expected = IllegalArgumentException.class)
	public void stringNula() {
		new RestNaoVazia<String>(new RestNaoNulo<>()).valido(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringVazia() {
		new RestNaoVazia<String>(new RestNaoNulo<>()).valido("");
	}

	@Test
	public void stringNaoVazia() {
		new RestNaoVazia<String>(new RestNaoNulo<>()).valido("Pedro");
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayVazio() {
		final Object[] a = {};
		new RestNaoVazia<Object>(new RestNaoNulo<>()).valido(a);
	}

	@Test
	public void arrayNaoVazio() {
		final Object[] a = { 1 };
		new RestNaoVazia<Object>(new RestNaoNulo<>()).valido(a);
	}

	@Test(expected = IllegalArgumentException.class)
	public void enumerationVazia() {
		new RestNaoVazia<Object>(new RestNaoNulo<>())
				.valido(Collections.emptyEnumeration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void listaVazia() {
		new RestNaoVazia<Object>(new RestNaoNulo<>())
				.valido(Collections.emptyList());
	}

	@Test(expected = IllegalArgumentException.class)
	public void iteratorVazio() {
		new RestNaoVazia<Object>(new RestNaoNulo<>())
				.valido(Collections.emptyIterator());
	}

	@Test(expected = IllegalArgumentException.class)
	public void mapaVazio() {
		new RestNaoVazia<Object>(new RestNaoNulo<>())
				.valido(Collections.emptyMap());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setVazio() {
		new RestNaoVazia<Object>(new RestNaoNulo<>())
				.valido(Collections.emptySet());
	}
}
