package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.media.Media;

public enum Sexo implements Documento {
	MASCULINO,
	FEMININO;

	@Override
	public Media print(final Media media) {
		return media.with("sexo", toString());
	}
}
