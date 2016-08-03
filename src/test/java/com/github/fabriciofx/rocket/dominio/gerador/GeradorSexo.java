package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Sexo;
import com.github.fabriciofx.rocket.misc.Rand;

public final class GeradorSexo {
	private final Rand aleatorio;
	
	public GeradorSexo() {
		aleatorio = new Rand();
	}

	public Sexo get() {
		final Sexo[] sexos = Sexo.values();
		final Sexo sexo = sexos[aleatorio.num(0, sexos.length - 1)];

		return sexo;
	}

	public String getString() {
		return get().toString();
	}
}
