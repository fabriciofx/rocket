package com.github.fabriciofx.rocket.dominio.repositorio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.id.IdHash;

public final class TesteHashId {
	@Test
	public void md5() throws Exception {
		final IdHash id = new IdHash("0388caab4a914cda72e61ec0bad75317");
		assertEquals("0388caab4a914cda72e61ec0bad75317", id.toString());
	}
}
