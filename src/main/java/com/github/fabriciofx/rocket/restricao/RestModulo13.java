package com.github.fabriciofx.rocket.restricao;

public final class RestModulo13<T extends CharSequence>
		implements Restricao<T> {
	private final transient RestPadrao<T> origem;

	public RestModulo13(final RestNaoVazia<T> origem) {
		this.origem = new RestPadrao<T>(origem, "^[\\d]{14}$");
	}

	@Override
	public T valido(final T objeto) {
		return modulo13(origem.valido(objeto));
	}

	private static <T> T modulo13(final T objeto) {
		final String digitosStr = objeto.toString();
		final int[] digitos = new int[12];
		final int dv1 = digitosStr.charAt(12) - '0';
		final int dv2 = digitosStr.charAt(13) - '0';
		for (int i = 0; i < digitos.length; i++) {
			digitos[i] = digitosStr.charAt(i) - '0';
		}
		int v1 = 5 * digitos[0] + 4 * digitos[1] + 3 * digitos[2]
				+ 2 * digitos[3] + 9 * digitos[4] + 8 * digitos[5]
				+ 7 * digitos[6] + 6 * digitos[7] + 5 * digitos[8]
				+ 4 * digitos[9] + 3 * digitos[10] + 2 * digitos[11];
		v1 = 11 - v1 % 11;
		if (v1 >= 10) {
			v1 = 0;
		}
		int v2 = 6 * digitos[0] + 5 * digitos[1] + 4 * digitos[2]
				+ 3 * digitos[3] + 2 * digitos[4] + 9 * digitos[5]
				+ 8 * digitos[6] + 7 * digitos[7] + 6 * digitos[8]
				+ 5 * digitos[9] + 4 * digitos[10] + 3 * digitos[11] + 2 * v1;

		v2 += 2 * v1;
		v2 = 11 - v2 % 11;
		if (v2 >= 10) {
			v2 = 0;
		}
		if (v1 != dv1 && v2 != dv2) {
			throw new IllegalArgumentException(
					"o número não confere com os dígitos verificadores");
		}
		return objeto;
	}
}
