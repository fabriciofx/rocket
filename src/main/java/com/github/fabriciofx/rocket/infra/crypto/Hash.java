package com.github.fabriciofx.rocket.infra.crypto;

import java.io.IOException;

public interface Hash {
	byte[] digest(final byte[] dados) throws IOException;
}
