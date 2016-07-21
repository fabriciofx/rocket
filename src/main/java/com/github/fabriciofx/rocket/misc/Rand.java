package com.github.fabriciofx.rocket.misc;

import java.util.Random;

public final class Rand {
	private final transient Random random;

	public Rand() {
		random = new Random();
	}

	public int num(final int limit) {
		return random.nextInt(limit);
	}

	public int num(final int min, final int max) {
		return random.nextInt((max + 1) - min) + min;
	}

	public long num(final long min, final long max) {
		return nextLong((max + 1) - min) + min;
	}

	public int[] nums(final int quantity) {
		final int[] nums = new int[quantity];
		for (int i = 0; i < quantity; i++) {
			nums[i] = random.nextInt(10);
		}
		return nums;
	}

	public String nums2(final int quantity) {
		final StringBuilder sb = new StringBuilder();
		final int[] numeros = nums(quantity);
		for (int i = 0; i < quantity; i++) {
			sb.append(numeros[i]);
		}
		return sb.toString();
	}

	public char character() {
		return (char) num(65, 90);
	}

	private long nextLong(final long n) {
		long bits, val;
		do {
			bits = (random.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits - val + (n - 1) < 0L);
		return val;
	}
}
