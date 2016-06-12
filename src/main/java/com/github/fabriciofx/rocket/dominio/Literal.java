package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public abstract class Literal {
	private final transient String conteudo;

	public Literal(final String conteudo) {
		this.conteudo = new NotEmpty<String>(new NotNull<>())
				.valid(conteudo);
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
