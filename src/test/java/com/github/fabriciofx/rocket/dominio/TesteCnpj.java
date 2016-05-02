package com.github.fabriciofx.rocket.dominio;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.pessoa.Cnpj;

public final class TesteCnpj {
	@Test
	public void valido() {
		new Cnpj("33014556000196");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeNulo() {
		new Cnpj(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeVazio() {
		new Cnpj("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeTiverTrezeDigitos() {
		new Cnpj("3014556000196");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeTiverQuinzeDigitos() {
		new Cnpj("301455600019612");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalido() {
		new Cnpj("34014556000196");
	}
}
