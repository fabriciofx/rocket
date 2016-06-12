package com.github.fabriciofx.rocket.security;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;

public final class Password {
	private final transient Hash hash;
	private final transient String content;

	public Password(final String conteudo) throws IOException {
		this(new Sha256(), conteudo);
	}

	public Password(final Hash hash, final String content) throws IOException {
		this.hash = new NotNull<Hash>().valid(hash);
		this.content = new Hex(hash)
				.content(new NotNull<String>().valid(content).getBytes());
	}

	@Override
	public String toString() {
		return content;
	}
}
