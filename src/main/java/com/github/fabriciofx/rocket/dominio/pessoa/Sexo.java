package com.github.fabriciofx.rocket.dominio.pessoa;

import com.github.fabriciofx.rocket.dominio.Documento;
import com.github.fabriciofx.rocket.media.Media;

public enum Sexo implements Documento {
	MASCULINO,
	FEMININO;

	@Override
	public Media print(Media media) {
		return media.with("sexo", this.toString());
	}
}
