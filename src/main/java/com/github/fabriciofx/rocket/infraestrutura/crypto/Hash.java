package com.github.fabriciofx.rocket.infraestrutura.crypto;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

public abstract class Hash {
	private final MessageDigest md;

	public Hash(final MessageDigest md) {
		this.md = md;
	}

	public String hash(final byte[] dados) {
		md.update(dados);

		return hex(md.digest());
	}

	public String hash(final String nomeArquivo) throws Exception {
		return hash(Files.readAllBytes(new File(nomeArquivo).toPath()));
	}

	@Override
	public String toString() {
		return hex(md.digest());
	}

	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private String hex(final byte[] hash) {
		final char[] hexChars = new char[hash.length * 2];

		int c = 0;
		for (int i = 0; i < hash.length; i++) {
			final int v = 0xFF & hash[i];
			hexChars[c++] = HEX_CHARS[v >>> 4];
			hexChars[c++] = HEX_CHARS[v & 0x0F];
		}

		return new String(hexChars);
	}
}
