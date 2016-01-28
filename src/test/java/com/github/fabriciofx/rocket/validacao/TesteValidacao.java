package com.github.fabriciofx.rocket.validacao;

public final class TesteValidacao {
	public static void main(String[] args) throws Exception {
		final Restricoes<String> restricoes = new Restricoes(new Nulo(), new Vazia());
		final String nome = "pedro";
		restricoes.valida(nome);
	}
}
