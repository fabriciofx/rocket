package com.github.fabriciofx.rocket.range;

public final class DefaultRange<T extends Comparable<T>> implements Range<T> {
	private final transient Limit<T> min;
	private final transient Limit<T> max;

	public DefaultRange(final Limit<T> min, final Limit<T> max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean contains(T value) {
		return !min.greater(value) && !max.less(value);
	}
}
