package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.security.Hash;
import com.github.fabriciofx.rocket.security.Hex;
import com.github.fabriciofx.rocket.security.Sha256;

public final class Password {
	private final transient String content;

	public Password(final String content) throws IOException {
		this(new Sha256(), content);
	}

	public Password(final Hash hash, final String content) throws IOException {
		this.content = new Hex(
			new NotNull<Hash>().valid(hash).digest(
				new NotNull<String>().valid(content).getBytes()
			)
		).toString();
	}

	@Override
	public String toString() {
		return content;
	}
}
