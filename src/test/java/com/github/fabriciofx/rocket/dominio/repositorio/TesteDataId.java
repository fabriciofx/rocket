package com.github.fabriciofx.rocket.dominio.repositorio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.github.fabriciofx.rocket.id.IdTime;
import com.github.fabriciofx.rocket.id.Id;

public final class TesteDataId {
	@Test
	public void comUmNumero() {
		final Id id = new IdTime(2015122920514373623L);
		assertEquals(2015122920514373623L, id.toLong());
	}

	@Test
	public void comDataHoraAtual() {
		final LocalDateTime agora = LocalDateTime.now();
	}
}
