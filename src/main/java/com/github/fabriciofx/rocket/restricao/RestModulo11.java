package com.github.fabriciofx.rocket.restricao;

public final class RestModulo11<T extends CharSequence> extends Restricao<T> {
	public RestModulo11(final RestPadrao<T> restricao) {
		super(valida(restricao.objeto()));
	}

	private static <T> T valida(final T objeto) {
		final String digitos = objeto.toString();
		final int tamanho = digitos.length();
		final int tamanho1 = tamanho - 1;
		final int tamanho2 = tamanho - 2;
		final int dv1 = digitos.charAt(tamanho2) - '0';
		final int dv2 = digitos.charAt(tamanho1) - '0';
		int v1 = 0, v2 = 0;

		for (int i = 0; i < tamanho2; i++) {
			final int digito = digitos.charAt(tamanho2 - 1 - i) - '0';
			v1 = v1 + digito * (tamanho2 - (i % tamanho1));
			v2 = v2 + digito * (tamanho2 - ((i + 1) % tamanho1));
		}
		
		v1 = (v1 % tamanho) % tamanho1;
		v2 = v2 + v1 * tamanho2;
		v2 = (v2 % tamanho) % tamanho1;

		if (v1 != dv1 && v2 != dv2) {
			throw new IllegalArgumentException(
					"o número não confere com os dígitos verificadores");
		}

		return objeto;
	}
}
