package com.github.fabriciofx.rocket.dominio.gerador;

import java.time.LocalDate;

import com.github.fabriciofx.rocket.dominio.doc.Rg;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Estado;
import com.github.fabriciofx.rocket.misc.Rand;

public final class GeradorRG {
	private final Rand aleatorio;

	public GeradorRG() {
		aleatorio = new Rand();
	}

	public Rg get() {
		final String numero = String
				.valueOf(aleatorio.num(100000000L, 999999999L));
		final Rg.Emissor[] emissores = Rg.Emissor.values();
		final Rg.Emissor emissor = emissores[aleatorio.num(0,
				emissores.length - 1)];
		final Estado[] estados = Estado.values();
		final Estado estado = estados[aleatorio.num(0, estados.length - 1)];
		final int via = aleatorio.num(1, 2);

		return new Rg(numero, emissor, estado, via, LocalDate.now());
	}

	public String getString() {
		return get().toString();
	}
}
