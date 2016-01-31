package com.github.fabriciofx.rocket.infra.crypto;

import java.security.MessageDigest;

public final class Sha1 extends Hash {
	public Sha1() throws Exception {
		super(MessageDigest.getInstance("SHA-1"));
	}
}
