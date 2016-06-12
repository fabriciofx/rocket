package com.github.fabriciofx.rocket.security;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.misc.Hex;

public final class Password {
	private final transient Hash hash;
	private final transient String conteudo;

	public Password(final String conteudo) throws IOException {
		this(new Sha256(), conteudo);
	}

	public Password(final Hash hash, final String conteudo) throws IOException {
		this.hash = new NotNull<Hash>().valid(hash);
		this.conteudo = new Hex(hash).conteudo(
				new NotNull<String>().valid(conteudo).getBytes());
	}

	@Override
	public String toString() {
		return conteudo;
	}
}
