package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.infra.media.Media;

public interface Documento {
	Media print(Media media) throws IOException;
}
