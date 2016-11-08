package com.github.fabriciofx.rocket.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class HashTest {
	private static final String DATA = "08a67fde-5a33-4063-8873-1c3933dc54fe";

	@Test
	public void md5() throws Exception {
		final String hash = new Hex(
			new Md5().digest(
				DATA.getBytes()
			)
		).toString();
		assertEquals("615fb9e70c7987dc9273cfd7381cc803", hash);
	}

	@Test
	public void sha1() throws Exception {
		final String hash = new Hex(
			new Sha1().digest(
				DATA.getBytes()
			)
		).toString();
		assertEquals("de01f6613de12a6d8926a84c287f437ff019beac", hash);
	}

	@Test
	public void sha256() throws Exception {
		final String hash = new Hex(
			new Sha256().digest(
				DATA.getBytes()
			)
		).toString();
		assertEquals(
			"7fb67f59dff99f4527e67a40b78fcbcd265b166977bbf211bc136902365c30a5",
			hash
		);
	}
}
