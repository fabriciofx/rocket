package com.github.fabriciofx.rocket.dominio.endereco;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.endereco.Cep;

public final class TesteCep {
	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeNulo() {
		new Cep(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeVazio() {
		new Cep("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeContemLetra() {
		new Cep("580A7030");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSePossuirHifen() {
		new Cep("58037-030");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSePossuirMaisDe8Digitos() {
		new Cep("580370301");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSePossuirMenosDe8Digitos() {
		new Cep("5803703");
	}
}
