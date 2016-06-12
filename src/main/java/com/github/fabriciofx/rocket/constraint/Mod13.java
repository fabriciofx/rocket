package com.github.fabriciofx.rocket.constraint;

public final class Mod13<T extends CharSequence> implements Constraint<T> {
	private final transient Pattern<T> origin;

	public Mod13(final NotEmpty<T> origin) {
		this.origin = new Pattern<T>(origin, "^[\\d]{14}$");
	}

	@Override
	public T valid(final T object) {
		return mod13(origin.valid(object));
	}

	private static <T> T mod13(final T object) {
		final String digitsStr = object.toString();
		final int[] digits = new int[12];
		final int vd1 = digitsStr.charAt(12) - '0';
		final int vd2 = digitsStr.charAt(13) - '0';
		for (int i = 0; i < digits.length; i++) {
			digits[i] = digitsStr.charAt(i) - '0';
		}
		int v1 = 5 * digits[0] + 4 * digits[1] + 3 * digits[2] + 2 * digits[3]
			+ 9 * digits[4] + 8 * digits[5] + 7 * digits[6] + 6 * digits[7]
			+ 5 * digits[8] + 4 * digits[9] + 3 * digits[10] + 2 * digits[11];
		v1 = 11 - v1 % 11;
		if (v1 >= 10) {
			v1 = 0;
		}
		int v2 = 6 * digits[0] + 5 * digits[1] + 4 * digits[2] + 3 * digits[3]
			+ 2 * digits[4] + 9 * digits[5] + 8 * digits[6] + 7 * digits[7]
			+ 6 * digits[8] + 5 * digits[9] + 4 * digits[10] + 3 * digits[11]
			+ 2 * v1;
		v2 += 2 * v1;
		v2 = 11 - v2 % 11;
		if (v2 >= 10) {
			v2 = 0;
		}
		if (v1 != vd1 && v2 != vd2) {
			throw new IllegalArgumentException(
				String.format("check digit verification failed on '%s'",
					digitsStr
				)
			);
		}
		return object;
	}
}
