package com.github.fabriciofx.rocket.dominio.documento;

import com.github.fabriciofx.rocket.media.Media;

public enum Titulacao implements Documento {
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

	@Override
	public Media print(final Media media) {
		return media.with("titulacao", abreviatura);
	}
}
