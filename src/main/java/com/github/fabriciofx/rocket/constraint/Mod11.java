package com.github.fabriciofx.rocket.constraint;

public final class Mod11<T extends CharSequence> implements Constraint<T> {
	private final transient Pattern<T> origin;
	
	public Mod11(final NotEmpty<T> origin) {
		this.origin = new Pattern<T>(origin, "^[\\d]{11}$");
	}

	@Override
	public T valid(final T object) {
		return mod11(origin.valid(object));
	}

	private static <T> T mod11(final T object) {
		final String digits = object.toString();
		final int length = digits.length();
		final int vd1 = digits.charAt(length - 2) - '0';
		final int vd2 = digits.charAt(length - 1) - '0';
		int v1 = 0, v2 = 0;
		for (int i = 0; i < length - 2; i++) {
			final int digit = digits.charAt(length - 3 - i) - '0';
			v1 = v1 + digit * (9 - (i % 10));
			v2 = v2 + digit * (9 - ((i + 1) % 10));
		}
		v1 = (v1 % 11) % 10;
		v2 = v2 + v1 * 9;
		v2 = (v2 % 11) % 10;
		if (v1 != vd1 && v2 != vd2) {
			throw new IllegalArgumentException(
				String.format("check digit verification failed on '%s'", digits)
			);
		}
		return object;
	}
}
