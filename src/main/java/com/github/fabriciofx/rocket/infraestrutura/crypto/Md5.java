package com.github.fabriciofx.rocket.infraestrutura.crypto;

import java.security.MessageDigest;

public final class Md5 extends Hash {
	public Md5() throws Exception {
		super(MessageDigest.getInstance("MD5"));
	}
}
