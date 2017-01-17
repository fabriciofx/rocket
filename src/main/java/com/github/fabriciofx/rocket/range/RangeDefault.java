package com.github.fabriciofx.rocket.range;

import com.github.fabriciofx.rocket.media.Media;

public final class RangeDefault<T extends Comparable<T>> implements Range<T> {
	private final transient Limit<T> min;
	private final transient Limit<T> max;

	public RangeDefault(final Limit<T> min, final Limit<T> max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean contains(final T value) {
		return !min.greater(value) && !max.less(value);
	}

	@Override
	public Media about(final Media media) {
		return media
			.with("min", min.toString())
			.with("max", max.toString());
	}
}
