package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.range.Range;

public interface Periodo<T extends Comparable<T>> extends Range<T> {
	T inicio();

	T termino();
	
	Media print(Media media);
}
