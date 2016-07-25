package com.github.fabriciofx.rocket.security;

import java.io.IOException;

public final class Plain implements Hash {
	@Override
	public byte[] digest(byte[] data) throws IOException {
		return data;
	}
}
