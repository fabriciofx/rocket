package com.github.fabriciofx.rocket.security;

import java.io.IOException;

public interface Hash {
	byte[] digest(final byte[] dados) throws IOException;
}
