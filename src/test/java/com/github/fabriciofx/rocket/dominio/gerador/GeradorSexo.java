package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.pessoa.Sexo;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class GeradorSexo {
	private final Aleatorio aleatorio;
	
	public GeradorSexo() {
		aleatorio = new Aleatorio();
	}

	public Sexo get() {
		final Sexo[] sexos = Sexo.values();
		final Sexo sexo = sexos[aleatorio.numero(0, sexos.length - 1)];

		return sexo;
	}

	public String getString() {
		return get().toString();
	}
}
