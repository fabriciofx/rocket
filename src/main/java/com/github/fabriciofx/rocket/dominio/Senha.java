package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.infra.crypto.Hash;
import com.github.fabriciofx.rocket.infra.crypto.Sha256;
import com.github.fabriciofx.rocket.misc.Hex;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class Senha {
	private final transient Hash hash;
	private final transient String conteudo;

	public Senha(final String conteudo) throws IOException {
		this(new Sha256(), conteudo);
	}

	public Senha(final Hash hash, final String conteudo) throws IOException {
		this.hash = new RestNaoNulo<Hash>().valido(hash);
		this.conteudo = new Hex(hash).conteudo(
				new RestNaoNulo<String>().valido(conteudo).getBytes());
	}

	@Override
	public String toString() {
		return conteudo;
	}
}
