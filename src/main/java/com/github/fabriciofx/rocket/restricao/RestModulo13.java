package com.github.fabriciofx.rocket.restricao;

public final class RestModulo13<T extends CharSequence> extends Restricao<T> {
	public RestModulo13(final RestPadrao<T> restricao) {
		super(valida(restricao.objeto()));
	}

	private static <T> T valida(final T objeto) {
		final String digitosStr = objeto.toString();

		// 3. Aloca um vetor de inteiros para converter a string recebida para
		// números.
		final int[] digitos = new int[14];

		for (int i = 0; i < digitos.length; i++) {
			digitos[i] = digitosStr.charAt(i) - '0';
		}

		// 4. Realiza o cálculo do 1o digito verificador.
		int digitoVerificador1 = 5 * digitos[0] + 4 * digitos[1]
				+ 3 * digitos[2] + 2 * digitos[3] + 9 * digitos[4]
				+ 8 * digitos[5] + 7 * digitos[6] + 6 * digitos[7]
				+ 5 * digitos[8] + 4 * digitos[9] + 3 * digitos[10]
				+ 2 * digitos[11];
		digitoVerificador1 = 11 - digitoVerificador1 % 11;

		if (digitoVerificador1 >= 10) {
			digitoVerificador1 = 0;
		}

		// 5. Realiza o cálculo do 2o digito verificador.
		int digitoVerificador2 = 6 * digitos[0] + 5 * digitos[1]
				+ 4 * digitos[2] + 3 * digitos[3] + 2 * digitos[4]
				+ 9 * digitos[5] + 8 * digitos[6] + 7 * digitos[7]
				+ 6 * digitos[8] + 5 * digitos[9] + 4 * digitos[10]
				+ 3 * digitos[11] + 2 * digitoVerificador1;

		digitoVerificador2 += 2 * digitoVerificador1;
		digitoVerificador2 = 11 - digitoVerificador2 % 11;

		if (digitoVerificador2 >= 10) {
			digitoVerificador2 = 0;
		}

		// 6. Verifica se os dígitos verificadores estão corretos.
		if (digitoVerificador1 != digitos[12]
				&& digitoVerificador2 != digitos[13]) {
			throw new IllegalArgumentException(
					"o número não confere com os dígitos verificadores");
		}

		return objeto;
	}
}
