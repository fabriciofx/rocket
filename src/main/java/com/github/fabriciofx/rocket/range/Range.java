package com.github.fabriciofx.rocket.range;

import com.github.fabriciofx.rocket.media.Media;

public interface Range<T extends Comparable<T>> {
	boolean contains(T value);
	
	Media print(Media media);
}
