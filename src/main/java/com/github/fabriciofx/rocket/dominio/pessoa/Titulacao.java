package com.github.fabriciofx.rocket.dominio.pessoa;

import com.github.fabriciofx.rocket.dominio.Elemento;

public enum Titulacao implements Elemento {
	MESTRE("M.e"),
	MESTRA("M.a"),
	DOUTOR("D.r"),
	DOUTORA("D.ra"),
	DOUTORES("Drs."),
	DOUTORAS("Dr.as");

	private final transient String abreviatura;

	Titulacao(final String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Override
	public String toString() {
		return abreviatura;
	}
}
