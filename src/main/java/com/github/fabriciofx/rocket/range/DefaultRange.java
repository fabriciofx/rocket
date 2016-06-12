package com.github.fabriciofx.rocket.range;

import com.github.fabriciofx.rocket.media.Media;

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

	@Override
	public Media print(Media media) {
		return media
			.with("min", min.toString())
			.with("max", max.toString());
	}
}
