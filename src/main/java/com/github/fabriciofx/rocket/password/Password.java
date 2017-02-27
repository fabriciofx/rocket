package com.github.fabriciofx.rocket.password;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.security.Hash;
import com.github.fabriciofx.rocket.security.Hex;
import com.github.fabriciofx.rocket.security.Sha256;

public final class Password {
	private final Hash hash;
	private final String content;

	public Password(final String content) {
		this(new Sha256(), content);
	}

	public Password(final Hash hash, final String content) {
		this.hash = hash;
		this.content = content;
	}

	@Override
	public String toString() {
		try {
			return new Hex(
				new NotNull<Hash>().valid(hash).digest(
					new NotNull<String>().valid(content).getBytes()
				)
			).toString();
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
