package com.github.fabriciofx.rocket.constraint;

public final class NotNull<T> implements Constraint<T> {
	@Override
	public T valid(final T object) {
		if (object == null) {
			throw new IllegalArgumentException("the object is null");
		}
		return object;
	}
}
