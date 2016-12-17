package com.github.fabriciofx.rocket.range;

import com.github.fabriciofx.rocket.media.About;

public interface Range<T extends Comparable<T>> extends About {
	boolean contains(T value);
}
