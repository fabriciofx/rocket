package com.github.fabriciofx.rocket.validacao;

public final class TesteValidacao {
	public static void main(String[] args) throws Exception {
		final String fone = "999993456";
		final Validacao<String> restricao = new ValidacaoNaoNulo<>(
				new ValidacaoNaoVazia<>(new ValidacaoRegEx<>()));
		restricao.valida(fone);
	}
}
