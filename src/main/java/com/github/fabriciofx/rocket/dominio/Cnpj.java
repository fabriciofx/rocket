package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class Cnpj {
	private final String numero;

	public Cnpj(final String numero) {
		valida(numero);
		this.numero = numero;
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cnpj
				&& numero.equals(Cnpj.class.cast(o).numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public String toString() {
		return numero;
	}

	private void valida(final String numero) {
		// 1. Verifica se a string é nula
		Objects.requireNonNull(numero,
				"argumento 'numero' de CNPJ não pode ser NULL");

		// 2. Verifica se a quantidade de caracteres é exatamente 14 (dígitos)
		if (!numero.matches("^[\\d]{14}$")) {
			throw new IllegalArgumentException(
			"argumento 'numero' de CNPJ deve possuir exatamente 14 dígitos");
		}

		// 3. Aloca um vetor de inteiros para converter a string recebida para
		// números.
		final int[] digitos = new int[14];

		for (int i = 0; i < digitos.length; i++) {
			digitos[i] = numero.charAt(i) - '0';
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
		"argumento 'numero' de CNPJ não confere com os dígitos verificadores");
		}
	}
}
