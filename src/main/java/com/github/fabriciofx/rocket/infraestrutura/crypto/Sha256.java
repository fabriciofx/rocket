package com.github.fabriciofx.rocket.infraestrutura.crypto;

import java.security.MessageDigest;

public final class Sha256 extends Hash {
	public Sha256() throws Exception {
		super(MessageDigest.getInstance("SHA-256"));
	}
}
