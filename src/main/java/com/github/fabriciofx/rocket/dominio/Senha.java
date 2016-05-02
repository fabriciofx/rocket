package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.infra.crypto.Hash;
import com.github.fabriciofx.rocket.infra.crypto.Sha256;
import com.github.fabriciofx.rocket.misc.Hex;

public final class Senha extends Literal implements Elemento {
	private final transient Hash hash;

	public Senha(final String conteudo) {
		this(new Sha256(), conteudo);
	}

	public Senha(final Hash hash, final String conteudo) {
		super(conteudo);
		this.hash = hash;
	}

	@Override
	public String toString() {
		try {
			return new Hex(hash).conteudo(super.toString().getBytes());
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
