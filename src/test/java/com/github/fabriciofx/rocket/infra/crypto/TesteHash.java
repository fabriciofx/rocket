package com.github.fabriciofx.rocket.infra.crypto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.crypto.Md5;
import com.github.fabriciofx.rocket.infra.crypto.Sha1;
import com.github.fabriciofx.rocket.infra.crypto.Sha256;
import com.github.fabriciofx.rocket.misc.Hex;

public class TesteHash {
	private static final String DADOS = "08a67fde-5a33-4063-8873-1c3933dc54fe";

	@Test
	public void md5() throws Exception {
		final Hex hex = new Hex(new Md5());
		final String digest = hex.conteudo(DADOS.getBytes());

		assertEquals("615fb9e70c7987dc9273cfd7381cc803", digest);
	}

	@Test
	public void sha1() throws Exception {
		final Hex hex = new Hex(new Sha1());
		final String digest = hex.conteudo(DADOS.getBytes());

		assertEquals("de01f6613de12a6d8926a84c287f437ff019beac", digest);
	}

	@Test
	public void sha256() throws Exception {
		final Hex hex = new Hex(new Sha256());
		final String digest = hex.conteudo(DADOS.getBytes());

		assertEquals(
				"7fb67f59dff99f4527e67a40b78fcbcd265b166977bbf211bc136902365c30a5",
				digest);
	}
}
