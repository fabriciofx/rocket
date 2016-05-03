package com.github.fabriciofx.rocket.restricao;

public final class RestModulo11<T extends CharSequence> implements Restricao<T> {
	private final transient RestPadrao<T> origem;
	
	public RestModulo11(final RestNaoVazia<T> origem) {
		this.origem = new RestPadrao<T>(origem, "^[\\d]{11}$");
	}

	@Override
	public T valido(final T objeto) {
		return modulo11(origem.valido(objeto));
	}

	private static <T> T modulo11(final T objeto) {
		final String digitos = objeto.toString();
		final int tamanho = digitos.length();
		final int dv1 = digitos.charAt(tamanho - 2) - '0';
		final int dv2 = digitos.charAt(tamanho - 1) - '0';
		int v1 = 0, v2 = 0;
		for (int i = 0; i < tamanho - 2; i++) {
			final int digito = digitos.charAt(tamanho - 3 - i) - '0';
			v1 = v1 + digito * (9 - (i % 10));
			v2 = v2 + digito * (9 - ((i + 1) % 10));
		}
		v1 = (v1 % 11) % 10;
		v2 = v2 + v1 * 9;
		v2 = (v2 % 11) % 10;
		if (v1 != dv1 && v2 != dv2) {
			throw new IllegalArgumentException(
					"o número não confere com os dígitos verificadores");
		}
		return objeto;
	}
}
