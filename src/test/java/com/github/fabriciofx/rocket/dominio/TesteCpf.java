package com.github.fabriciofx.rocket.dominio;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.doc.Cpf;

public final class TesteCpf {
	@Test
	public void valido() {
		new Cpf("24793105580");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoComNull() {
		new Cpf(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoComStringVazia() {
		new Cpf("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoComDezDigitos() {
		new Cpf("2479315580");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoComDozeDigitos() {
		new Cpf("247293105580");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalido() {
		new Cpf("24793205580");
	}
}
