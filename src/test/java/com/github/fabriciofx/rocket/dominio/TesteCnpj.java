package com.github.fabriciofx.rocket.dominio;

import org.junit.Test;

public class TesteCnpj {
	@Test
	public void cnpjValido() {
		new Cnpj("33014556000196");
	}

	@Test(expected = NullPointerException.class)
	public void invalidoComNull() {
		new Cnpj(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoComStringVazia() {
		new Cnpj("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoComTrezeDigitos() {
		new Cnpj("3014556000196");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidoComQuinzeDigitos() {
		new Cnpj("301455600019612");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalido() {
		new Cnpj("34014556000196");
	}
}
