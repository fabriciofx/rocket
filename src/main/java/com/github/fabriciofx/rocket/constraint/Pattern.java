package com.github.fabriciofx.rocket.constraint;

public final class Pattern<T> implements Constraint<T> {
	private final transient NotEmpty<T> origin;
	private final transient String regex;

	public Pattern(final NotEmpty<T> origem, final String regex) {
		this.origin = origem;
		this.regex = new NotEmpty<String>(new NotNull<>()).valid(regex);
	}

	@Override
	public T valid(final T object) {
		origin.valid(object);
		if (!object.toString().matches(regex)) {
			throw new IllegalArgumentException(
				"'%s' don't maches with the pattern"
			);
		}
		return object;
	}
}
