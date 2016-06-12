package com.github.fabriciofx.rocket.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Sha1 implements Hash {
	@Override
	public byte[] digest(final byte[] data) throws IOException {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-1");
			return md.digest(data);
		} catch (final NoSuchAlgorithmException e) {
			throw new IOException(e);
		}
	}
}