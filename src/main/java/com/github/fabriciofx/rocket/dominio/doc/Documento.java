package com.github.fabriciofx.rocket.dominio.doc;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public interface Documento {
	Media print(Media media) throws IOException;
}
