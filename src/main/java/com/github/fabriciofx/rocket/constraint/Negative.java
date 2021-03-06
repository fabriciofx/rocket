package com.github.fabriciofx.rocket.constraint;

public final class Negative<T extends Number> implements Constraint<T> {
	private final transient NotNull<T> origin;
	
	public Negative(final NotNull<T> origin) {
		this.origin = origin;
	}

	@Override
	public T valid(final T object) {
		origin.valid(object);
		final double value = Number.class.cast(object).doubleValue();
		if (value >= 0) {
			throw new IllegalArgumentException(
				String.format("'%f' must be negative", value)
			);
		}
		return object;
	}
}
