package com.github.fabriciofx.rocket.range;

public interface Range<T extends Comparable<T>> {
	boolean contains(T value);
}
