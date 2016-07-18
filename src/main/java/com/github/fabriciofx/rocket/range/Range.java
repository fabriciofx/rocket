package com.github.fabriciofx.rocket.range;

import com.github.fabriciofx.rocket.media.Printer;

public interface Range<T extends Comparable<T>> extends Printer {
	boolean contains(T value);
}
