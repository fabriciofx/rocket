package com.github.fabriciofx.rocket.restricao;

public final class RestModulo11<T extends CharSequence> extends Restricao<T> {
	public RestModulo11(final RestPadrao<T> restricao) {
		super(valida(restricao.objeto()));
	}

	private static <T> T valida(final T objeto) {
		final int[] digitos = new int[11];
		final String digitosStr = objeto.toString();
		
		for (int i = 0; i < digitos.length; i++) {
			digitos[i] = digitosStr.charAt(i) - '0';
		}

		// 4. Realiza o cálculo do 1o digito verificador.
		int digitoVerificador1 = 10 * digitos[0] + 9 * digitos[1]
				+ 8 * digitos[2] + 7 * digitos[3] + 6 * digitos[4]
				+ 5 * digitos[5] + 4 * digitos[6] + 3 * digitos[7]
				+ 2 * digitos[8];
		digitoVerificador1 = 11 - digitoVerificador1 % 11;

		if (digitoVerificador1 >= 10) {
			digitoVerificador1 = 0;
		}

		// 5. Realiza o cálculo do 2o digito verificador.
		int digitoVerificador2 = 11 * digitos[0] + 10 * digitos[1]
				+ 9 * digitos[2] + 8 * digitos[3] + 7 * digitos[4]
				+ 6 * digitos[5] + 5 * digitos[6] + 4 * digitos[7]
				+ 3 * digitos[8];

		digitoVerificador2 += 2 * digitoVerificador1;
		digitoVerificador2 = 11 - digitoVerificador2 % 11;

		if (digitoVerificador2 >= 10) {
			digitoVerificador2 = 0;
		}

		// 6. Verifica se os dígitos verificadores estão corretos.
		if (digitoVerificador1 != digitos[9]
				&& digitoVerificador2 != digitos[10]) {
			throw new IllegalArgumentException(
					"o número não confere com os dígitos verificadores");
		}

		return objeto;
	}
}
