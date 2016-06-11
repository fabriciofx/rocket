package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.infra.media.Media;

public interface Periodo<T extends Comparable<T>> extends Intervalo<T> {
	T inicio();

	T termino();
	
	Media print(Media media);
}
