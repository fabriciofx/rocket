package com.github.fabriciofx.rocket.dominio.gerador;

import java.time.LocalDate;

import com.github.fabriciofx.rocket.dominio.Rg;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class GeradorRG {
	private static final String[] ESTADOS = { "AC", "AL", "AM", "AP", "BA",
			"CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE",
			"PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };

	private final Aleatorio aleatorio;
	
	public GeradorRG() {
		aleatorio = new Aleatorio();
	}

	public Rg get() {
		final String numero = String.valueOf(
				aleatorio.numero(100000000L, 999999999L));
		final Rg.Emissor[] emissores = Rg.Emissor.values();
		final Rg.Emissor emissor = emissores[aleatorio.numero(0,
				emissores.length - 1)];
		final String estado = ESTADOS[aleatorio.numero(0,
				ESTADOS.length - 1)];
		final int via = aleatorio.numero(1, 2);

		return new Rg(numero, emissor, estado, via, LocalDate.now());
	}

	public String getString() {
		return get().toString();
	}
}
