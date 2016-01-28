package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class Cpf {
	private final String numero;

	public Cpf(final String numero) {
		valida(numero);
		this.numero = numero;
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Cpf
				&& numero.equals(Cpf.class.cast(o).numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public String toString() {
		return this.numero;
	}

	private void valida(final String numero) {
		// 1. Verifica se a string é nula ou vazia
		Objects.requireNonNull(numero,
				"o argumento 'numero' de CPF não pode ser NULL");

		// 2. Verifica se a quantidade de caracteres é exatamente 11 (dígitos)
		if (numero.matches("^[\\d]{11}$")) {
			throw new IllegalArgumentException(
				"argumento 'numero' de CPF deve possuir exatamente 11 dígitos");
		}

		// 3. Aloca um vetor de inteiros para converter a string recebida para
		// números.
		final int[] digitos = new int[11];

		for (int i = 0; i < digitos.length; i++) {
			digitos[i] = numero.charAt(i) - '0';
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
		"argumento 'numero' de CPF não confere com os dígitos verificadores");
		}
	}
}
