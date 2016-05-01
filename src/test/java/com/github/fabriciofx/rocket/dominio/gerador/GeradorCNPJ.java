package com.github.fabriciofx.rocket.dominio.gerador;

import java.util.Random;

import com.github.fabriciofx.rocket.dominio.Cnpj;

public final class GeradorCNPJ {
	private GeradorCNPJ() {
	}

	private static int[] getDigitos() {
		Random random = new Random();
		int[] digitosCNPJ = new int[14];

		for (int i = 0; i < 8; i++) {
			digitosCNPJ[i] = random.nextInt(10);
		}

		digitosCNPJ[8] = 0;
		digitosCNPJ[9] = 0;
		digitosCNPJ[10] = 0;
		digitosCNPJ[11] = 1;

		// 5. Realiza o cálculo do 1o digito verificador.
		int digitoVerificador1 = 5 * digitosCNPJ[0] + 4 * digitosCNPJ[1] + 3
				* digitosCNPJ[2] + 2 * digitosCNPJ[3] + 9 * digitosCNPJ[4] + 8
				* digitosCNPJ[5] + 7 * digitosCNPJ[6] + 6 * digitosCNPJ[7] + 5
				* digitosCNPJ[8] + 4 * digitosCNPJ[9] + 3 * digitosCNPJ[10] + 2
				* digitosCNPJ[11];
		digitoVerificador1 = 11 - digitoVerificador1 % 11;

		if (digitoVerificador1 >= 10) {
			digitoVerificador1 = 0;
		}

		// 6. Realiza o cálculo do 2o digito verificador.
		int digitoVerificador2 = 6 * digitosCNPJ[0] + 5 * digitosCNPJ[1] + 4
				* digitosCNPJ[2] + 3 * digitosCNPJ[3] + 2 * digitosCNPJ[4] + 9
				* digitosCNPJ[5] + 8 * digitosCNPJ[6] + 7 * digitosCNPJ[7] + 6
				* digitosCNPJ[8] + 5 * digitosCNPJ[9] + 4 * digitosCNPJ[10] + 3
				* digitosCNPJ[11] + 2 * digitoVerificador1;

		digitoVerificador2 += 2 * digitoVerificador1;
		digitoVerificador2 = 11 - digitoVerificador2 % 11;

		if (digitoVerificador2 >= 10) {
			digitoVerificador2 = 0;
		}

		digitosCNPJ[12] = digitoVerificador1;
		digitosCNPJ[13] = digitoVerificador2;

		return digitosCNPJ;
	}

	private static int[] getDigitosInvalidos() {
		int[] digitosCNPJ = getDigitos();

		int temp = digitosCNPJ[12];
		digitosCNPJ[12] = digitosCNPJ[13];
		digitosCNPJ[13] = temp;

		return digitosCNPJ;
	}

	public static Cnpj get() {
		return new Cnpj(getString());
	}

	public static String getString() {
		int[] digitosCNPJ = getDigitos();

		return String.format("%d%d%d%d%d%d%d%d%d%d%d%d%d%d", digitosCNPJ[0],
				digitosCNPJ[1], digitosCNPJ[2], digitosCNPJ[3], digitosCNPJ[4],
				digitosCNPJ[5], digitosCNPJ[6], digitosCNPJ[7], digitosCNPJ[8],
				digitosCNPJ[9], digitosCNPJ[10], digitosCNPJ[11],
				digitosCNPJ[12], digitosCNPJ[13]);
	}

	public static String getStringFormatado() {
		int[] digitosCNPJ = getDigitos();

		return String.format("%d%d.%d%d%d.%d%d%d/%d%d%d%d-%d%d",
				digitosCNPJ[0], digitosCNPJ[1], digitosCNPJ[2], digitosCNPJ[3],
				digitosCNPJ[4], digitosCNPJ[5], digitosCNPJ[6], digitosCNPJ[7],
				digitosCNPJ[8], digitosCNPJ[9], digitosCNPJ[10],
				digitosCNPJ[11], digitosCNPJ[12], digitosCNPJ[13]);
	}

	public static String getStringInvalido() {
		int[] digitosCNPJ = getDigitosInvalidos();

		return String.format("%d%d%d%d%d%d%d%d%d%d%d%d%d%d", digitosCNPJ[0],
				digitosCNPJ[1], digitosCNPJ[2], digitosCNPJ[3], digitosCNPJ[4],
				digitosCNPJ[5], digitosCNPJ[6], digitosCNPJ[7], digitosCNPJ[8],
				digitosCNPJ[9], digitosCNPJ[10], digitosCNPJ[11],
				digitosCNPJ[12], digitosCNPJ[13]);
	}

	public static String getStringInvalidoFormatado() {
		int[] digitosCNPJ = getDigitosInvalidos();

		return String.format("%d%d.%d%d%d.%d%d%d/%d%d%d%d-%d%d",
				digitosCNPJ[0], digitosCNPJ[1], digitosCNPJ[2], digitosCNPJ[3],
				digitosCNPJ[4], digitosCNPJ[5], digitosCNPJ[6], digitosCNPJ[7],
				digitosCNPJ[8], digitosCNPJ[9], digitosCNPJ[10],
				digitosCNPJ[11], digitosCNPJ[12], digitosCNPJ[13]);
	}
}
