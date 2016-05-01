package com.github.fabriciofx.rocket.dominio.repositorio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public final class TesteDataId {
	@Test
	public void comUmNumero() {
		final DataId id = new DataId(2015122920514373623L);
		assertEquals(2015122920514373623L, id.toLong());
	}

	@Test
	public void comDataHoraAtual() {
		final LocalDateTime agora = LocalDateTime.now();
		final DataId id = new DataId(agora);
		assertEquals(agora, id.dataHora());
	}
}
