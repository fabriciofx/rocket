package com.github.fabriciofx.rocket.infra.crypto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.crypto.Md5;
import com.github.fabriciofx.rocket.infra.crypto.Sha1;
import com.github.fabriciofx.rocket.infra.crypto.Sha256;

public class TesteHash {
	final String data = "08a67fde-5a33-4063-8873-1c3933dc54fe";

	@Test
	public void md5() throws Exception {
		final Md5 md5 = new Md5();
		final String hash = md5.hash(data.getBytes());

		assertEquals("615fb9e70c7987dc9273cfd7381cc803", hash);
	}

	@Test
	public void sha1() throws Exception {
		final Sha1 sha1 = new Sha1();
		final String hash = sha1.hash(data.getBytes());

		assertEquals("de01f6613de12a6d8926a84c287f437ff019beac", hash);
	}

	@Test
	public void sha256() throws Exception {
		final Sha256 sha256 = new Sha256();
		final String hash = sha256.hash(data.getBytes());

		assertEquals(
				"7fb67f59dff99f4527e67a40b78fcbcd265b166977bbf211bc136902365c30a5",
				hash);
	}
}
