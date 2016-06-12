package com.github.fabriciofx.rocket.infra.constraint;

import java.util.Collections;

import org.junit.Test;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class TesteNotEmpty {
	@Test(expected = IllegalArgumentException.class)
	public void stringNula() {
		new NotEmpty<String>(new NotNull<>()).valid(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void stringVazia() {
		new NotEmpty<String>(new NotNull<>()).valid("");
	}

	@Test
	public void stringNaoVazia() {
		new NotEmpty<String>(new NotNull<>()).valid("Pedro");
	}

	@Test(expected = IllegalArgumentException.class)
	public void arrayVazio() {
		final Object[] a = {};
		new NotEmpty<Object>(new NotNull<>()).valid(a);
	}

	@Test
	public void arrayNaoVazio() {
		final Object[] a = { 1 };
		new NotEmpty<Object>(new NotNull<>()).valid(a);
	}

	@Test(expected = IllegalArgumentException.class)
	public void enumerationVazia() {
		new NotEmpty<Object>(new NotNull<>())
				.valid(Collections.emptyEnumeration());
	}

	@Test(expected = IllegalArgumentException.class)
	public void listaVazia() {
		new NotEmpty<Object>(new NotNull<>())
				.valid(Collections.emptyList());
	}

	@Test(expected = IllegalArgumentException.class)
	public void iteratorVazio() {
		new NotEmpty<Object>(new NotNull<>())
				.valid(Collections.emptyIterator());
	}

	@Test(expected = IllegalArgumentException.class)
	public void mapaVazio() {
		new NotEmpty<Object>(new NotNull<>())
				.valid(Collections.emptyMap());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setVazio() {
		new NotEmpty<Object>(new NotNull<>())
				.valid(Collections.emptySet());
	}
}
