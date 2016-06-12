package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.documento.Fone;
import com.github.fabriciofx.rocket.dominio.simples.SimplesFone;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class GeradorFone {
	final Aleatorio aleatorio;

	public GeradorFone() {
		aleatorio = new Aleatorio();
	}

	public Fone get() {
		final Fone.Tipo[] tipos = Fone.Tipo.values();
		final Fone.Operadora[] operadoras = Fone.Operadora.values();

		final Fone.Tipo tipo = tipos[aleatorio.numero(0, tipos.length - 1)];
		final Fone.Operadora operadora = operadoras[aleatorio.numero(0,
				operadoras.length - 1)];

		return new SimplesFone(aleatorio.numeros2(8), tipo, operadora);
	}

	public String getString() {
		return get().toString();
	}
}
