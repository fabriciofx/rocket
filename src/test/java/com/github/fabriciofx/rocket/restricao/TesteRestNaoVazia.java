package com.github.fabriciofx.rocket.restricao;

import java.util.Collections;

import org.junit.Test;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class TesteRestNaoVazia {
	@Test(expected = IllegalArgumentException.class)
	public void stringNula() {
		new RestNaoVazia<String>(new RestNaoNulo<>(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringVazia() {
		new RestNaoVazia<String>(new RestNaoNulo<>(""));
	}

	@Test
	public void stringNaoVazia() {
		new RestNaoVazia<String>(new RestNaoNulo<>("Pedro"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayVazio() {
		Object[] a = {};
		new RestNaoVazia<Object>(new RestNaoNulo<>(a));
	}

	@Test
	public void arrayNaoVazio() {
		Object[] a = { 1 };
		new RestNaoVazia<Object>(new RestNaoNulo<>(a));
	}

	@Test(expected = IllegalArgumentException.class)
	public void enumerationVazia() {
		new RestNaoVazia<Object>(
				new RestNaoNulo<>(Collections.emptyEnumeration()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void listaVazia() {
		new RestNaoVazia<Object>(
				new RestNaoNulo<>(Collections.emptyList()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void iteratorVazio() {
		new RestNaoVazia<Object>(
				new RestNaoNulo<>(Collections.emptyIterator()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void mapaVazio() {
		new RestNaoVazia<Object>(
				new RestNaoNulo<>(Collections.emptyMap()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setVazio() {
		new RestNaoVazia<Object>(
				new RestNaoNulo<>(Collections.emptySet()));
	}
}
