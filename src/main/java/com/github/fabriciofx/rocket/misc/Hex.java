package com.github.fabriciofx.rocket.misc;

import java.io.IOException;

import com.github.fabriciofx.rocket.infra.crypto.Hash;

public final class Hex {
	private static final char[] HEX_CHARS = {
			'0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};
	
	private final Hash hash;
	
	public Hex(final Hash hash) {
		this.hash = hash;
	}

	public String conteudo(final byte[] dados) throws IOException {
		final byte[] digest = hash.digest(dados);
		final char[] hex = new char[digest.length * 2];
		for (int c = 0, i = 0; i < digest.length; i++) {
			final int v = 0xFF & digest[i];
			hex[c++] = HEX_CHARS[v >>> 4];
			hex[c++] = HEX_CHARS[v & 0x0F];
		}

		return new String(hex);
	}
}
