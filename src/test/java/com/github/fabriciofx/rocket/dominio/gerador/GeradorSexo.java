package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.doc.Sexo;
import com.github.fabriciofx.rocket.misc.Random;

public final class GeradorSexo {
	private final Random aleatorio;
	
	public GeradorSexo() {
		aleatorio = new Random();
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
