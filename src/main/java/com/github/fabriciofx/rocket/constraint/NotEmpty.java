package com.github.fabriciofx.rocket.constraint;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public final class NotEmpty<T> implements Constraint<T> {
	private final transient NotNull<T> origin;
	
	public NotEmpty(final NotNull<T> origin) {
		this.origin = origin;
	}

	@Override
	public T valid(final T object) {
		origin.valid(object);
		if (object instanceof CharSequence && CharSequence.class.cast(object)
				.toString().length() == 0) {
			throw new IllegalArgumentException("string is empty");
		} else if (object instanceof Object[]
				&& Object[].class.cast(object).length == 0) {
			throw new IllegalArgumentException("array is empty");
		} else if (object instanceof Iterator
				&& !Iterator.class.cast(object).hasNext()) {
			throw new IllegalArgumentException("iterator is empty");
		} else if (object instanceof Collection
				&& Collection.class.cast(object).isEmpty()) {
			throw new IllegalArgumentException(
				String.format("collection '%s' is empty",
					object.getClass().getSimpleName()
				)
			);
		} else if (object instanceof Map && Map.class.cast(object).isEmpty()) {
			throw new IllegalArgumentException(
				String.format("map '%s' is empty",
					object.getClass().getSimpleName()
				)
			);
		} else if (object instanceof Enumeration
				&& !Enumeration.class.cast(object).hasMoreElements()) {
			throw new IllegalArgumentException(
				String.format("enumeration '%s' is empty",
					object.getClass().getSimpleName()
				)
			);
		}
		return object;
	}
}
