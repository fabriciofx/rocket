package com.github.fabriciofx.rocket.range;

import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class RangeDefault<T extends Comparable<T>> implements Range<T> {
	private final Limit<T> min;
	private final Limit<T> max;
	private final Media media;

	public RangeDefault(final Limit<T> min, final Limit<T> max) {
		this(min, max, new XmlMedia("range"));
	}

	public RangeDefault(final Limit<T> min, final Limit<T> max,
		final Media media) {
		this.min = min;
		this.max = max;
		this.media = media;
	}

	@Override
	public boolean contains(final T value) {
		return !min.greater(value) && !max.less(value);
	}

	@Override
	public Media about() {
		return media
			.with("min", min.toString())
			.with("max", max.toString());
	}
}
