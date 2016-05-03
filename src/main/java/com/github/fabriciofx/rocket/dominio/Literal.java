package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;

public abstract class Literal {
	private final transient String conteudo;

	public Literal(final String conteudo) {
		this.conteudo = new RestNaoVazia<String>(new RestNaoNulo<>())
				.valido(conteudo);
	}

	@Override
	public int hashCode() {
		return 31 + ((conteudo == null) ? 0 : conteudo.hashCode());
	}

	@Override
	public String toString() {
		return conteudo;
	}
}
