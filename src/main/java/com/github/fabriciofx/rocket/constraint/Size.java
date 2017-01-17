package com.github.fabriciofx.rocket.constraint;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import com.github.fabriciofx.rocket.range.RangeDefault;

public final class Size<T> implements Constraint<T> {
	private final transient NotEmpty<T> origin;
	private final transient RangeDefault<Integer> range;

	public Size(final NotEmpty<T> origin, final RangeDefault<Integer> range) {
		this.origin = origin;
		this.range = new NotNull<RangeDefault<Integer>>().valid(range);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T valid(final T object) {
		origin.valid(object);
		if (object instanceof Collection
				&& !range.contains(Collection.class.cast(object).size())) {
			throw new IllegalArgumentException(
				"collection's size is out of range"
			);
		} else if (object instanceof CharSequence && !range.contains(
				CharSequence.class.cast(object).toString().trim().length())) {
			throw new IllegalArgumentException(
				String.format("string '%s' length is out of range",
					object.toString()
				)
			);
		} else if (object instanceof Map
				&& !range.contains(Map.class.cast(object).size())) {
			throw new IllegalArgumentException(
				"map's size is out of range"					
			);
		} else if (object instanceof Enumeration && !range.contains(
				Collections.list(Enumeration.class.cast(object)).size())) {
			throw new IllegalArgumentException(
				"enumeration's size is out of range"					
			);
		}
		return object;
	}
}
