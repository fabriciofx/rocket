package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.doc.endereco.Cep;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class GeradorCEP {
	private final Aleatorio aleatorio;

	public GeradorCEP() {
		aleatorio = new Aleatorio();
	}

	public Cep get() {
		return new Cep(aleatorio.numeros2(8));
	}

	public String getString() {
		return aleatorio.numeros2(8);
	}
}
