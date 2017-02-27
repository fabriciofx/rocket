package com.github.fabriciofx.rocket.password;

import java.io.IOException;

import com.github.fabriciofx.rocket.security.Hash;
import com.github.fabriciofx.rocket.security.Hex;
import com.github.fabriciofx.rocket.security.Sha256;

public final class PasswordCrypted implements Password {
	private final Password origin;
	private final Hash hash;

	public PasswordCrypted(final Password origin) {
		this(origin, new Sha256());
	}

	public PasswordCrypted(final Password origin, final Hash hash) {
		this.origin = origin;
		this.hash = hash;
	}

	@Override
	public String content() {
		try {
			return new Hex(
				this.hash.digest(
					this.origin.content().getBytes()
				)
			).toString();
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
