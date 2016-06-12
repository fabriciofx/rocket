package com.github.fabriciofx.rocket.dominio.gerador;

import java.util.Random;

import com.github.fabriciofx.rocket.dominio.documento.Cpf;

public final class GeradorCPF {
	private GeradorCPF() {
	}
	
	private static int[] getDigitos() {
		Random random = new Random();
		int[] digitosCPF = new int[11];

		for (int i = 0; i < 10; i++) {
			digitosCPF[i] = random.nextInt(10);
		}

		// Realiza o cálculo do 1o digito verificador.
		int digitoVerificador1 = 10 * digitosCPF[0] + 9 * digitosCPF[1] + 8
				* digitosCPF[2] + 7 * digitosCPF[3] + 6 * digitosCPF[4] + 5
				* digitosCPF[5] + 4 * digitosCPF[6] + 3 * digitosCPF[7] + 2
				* digitosCPF[8];
		digitoVerificador1 = 11 - digitoVerificador1 % 11;

		if (digitoVerificador1 >= 10) {
			digitoVerificador1 = 0;
		}

		// Realiza o cálculo do 2o digito verificador.
		int digitoVerificador2 = 11 * digitosCPF[0] + 10 * digitosCPF[1] + 9
				* digitosCPF[2] + 8 * digitosCPF[3] + 7 * digitosCPF[4] + 6
				* digitosCPF[5] + 5 * digitosCPF[6] + 4 * digitosCPF[7] + 3
				* digitosCPF[8];

		digitoVerificador2 += 2 * digitoVerificador1;
		digitoVerificador2 = 11 - digitoVerificador2 % 11;

		if (digitoVerificador2 >= 10) {
			digitoVerificador2 = 0;
		}

		digitosCPF[9] = digitoVerificador1;
		digitosCPF[10] = digitoVerificador2;

		return digitosCPF;
	}

	private static int[] getDigitosInvalidos() {
		int[] digitosCPF = getDigitos();

		int temp = digitosCPF[9];
		digitosCPF[9] = digitosCPF[10];
		digitosCPF[10] = temp;

		return digitosCPF;
	}

	public static Cpf get() {
		return new Cpf(getString());
	}
	
	public static Cpf getInvalido() {
		return new Cpf(getStringInvalido());
	}
	
	public static String getString() {
		int[] digitosCPF = getDigitos();

		return String.format("%d%d%d%d%d%d%d%d%d%d%d", digitosCPF[0],
				digitosCPF[1], digitosCPF[2], digitosCPF[3], digitosCPF[4],
				digitosCPF[5], digitosCPF[6], digitosCPF[7], digitosCPF[8],
				digitosCPF[9], digitosCPF[10]);
	}

	public static String getStringFormatada() {
		int[] digitosCPF = getDigitos();

		return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", digitosCPF[0],
				digitosCPF[1], digitosCPF[2], digitosCPF[3], digitosCPF[4],
				digitosCPF[5], digitosCPF[6], digitosCPF[7], digitosCPF[8],
				digitosCPF[9], digitosCPF[10]);
	}

	
	public static String getStringInvalido() {
		int[] digitosCPF = getDigitosInvalidos();

		return String.format("%d%d%d%d%d%d%d%d%d%d%d", digitosCPF[0],
				digitosCPF[1], digitosCPF[2], digitosCPF[3], digitosCPF[4],
				digitosCPF[5], digitosCPF[6], digitosCPF[7], digitosCPF[8],
				digitosCPF[9], digitosCPF[10]);
	}

	public static String getStringInvalidoFormatado() {
		int[] digitosCPF = getDigitosInvalidos();

		return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", digitosCPF[0],
				digitosCPF[1], digitosCPF[2], digitosCPF[3], digitosCPF[4],
				digitosCPF[5], digitosCPF[6], digitosCPF[7], digitosCPF[8],
				digitosCPF[9], digitosCPF[10]);
	}
}
