package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.infra.media.Media;

public interface Endereco {
	Media print(Media media) throws IOException;
}
