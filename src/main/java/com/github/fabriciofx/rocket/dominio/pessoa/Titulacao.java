package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.Serializable;

public enum Titulacao implements Serializable {
	MESTRE("M.e"),
	MESTRA("M.a"),
	DOUTOR("D.r"),
	DOUTORA("D.ra"),
	DOUTORES("Drs."),
	DOUTORAS("Dr.as");

	private final String abreviatura;

	Titulacao(final String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Override
	public String toString() {
		return abreviatura;
	}
}
