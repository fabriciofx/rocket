package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

public enum Titulacao implements Documento {
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
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(abreviatura);
	}

	@Override
	public Media print(final Media media) {
		return media.with("titulacao", toString());
	}
}
