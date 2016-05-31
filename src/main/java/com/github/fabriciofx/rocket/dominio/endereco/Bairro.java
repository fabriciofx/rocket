package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public final class Bairro implements Printer {
	private final String nome;

	public Bairro(final String nome) {
		this.nome = new RestNaoVazia<String>(new RestNaoNulo<>()).valido(nome);
	}

	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public Media print(Media media) {
		return media.with("bairro", nome);
	}
}
