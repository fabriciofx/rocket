package com.github.fabriciofx.rocket.misc;

import java.util.Random;

public final class Random {
	private final transient Random random;

	public Random() {
		random = new Random();
	}

	public int numero(final int limite) {
		return random.nextInt(limite);
	}

	public int num(final int min, final int max) {
		return random.nextInt((max + 1) - min) + min;
	}

	public long numero(final long min, final long max) {
		return nextLong((max + 1) - min) + min;
	}

	public int[] numeros(final int quantidade) {
		final int[] numeros = new int[quantidade];

		for (int i = 0; i < quantidade; i++) {
			numeros[i] = random.nextInt(10);
		}

		return numeros;
	}

	public String numeros2(final int quantidade) {
		final StringBuilder sb = new StringBuilder();
		int[] numeros = numeros(quantidade);

		for (int i = 0; i < quantidade; i++) {
			sb.append(numeros[i]);
		}

		return sb.toString();
	}

	public char caractere() {
		return (char) num(65, 90);
	}

	private long nextLong(final long n) {
		// error checking and 2^x checking removed for simplicity.
		long bits, val;
		do {
			bits = (random.nextLong() << 1) >>> 1;
			val = bits % n;
		} while (bits - val + (n - 1) < 0L);

		return val;
	}
}
