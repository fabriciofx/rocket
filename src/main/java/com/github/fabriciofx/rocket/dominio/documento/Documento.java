package com.github.fabriciofx.rocket.dominio.documento;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public interface Documento {
	Media print(Media media) throws IOException;
}
