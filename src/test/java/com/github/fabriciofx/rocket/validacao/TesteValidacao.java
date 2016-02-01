package com.github.fabriciofx.rocket.validacao;

public final class TesteValidacao {
	public static void main(String[] args) throws Exception {
		final String fone = "999993456";
		final Restricao<String> restricao = new NaoNulo<>(
				new StrNaoVazia<>(new StrNumerica<>()));
		restricao.valida(fone);
	}
}
