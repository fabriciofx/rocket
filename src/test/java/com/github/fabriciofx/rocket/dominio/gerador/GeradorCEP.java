package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.doc.endereco.Cep;
import com.github.fabriciofx.rocket.misc.Rand;

public final class GeradorCEP {
	private final Rand aleatorio;

	public GeradorCEP() {
		aleatorio = new Rand();
	}

	public Cep get() {
		return new Cep(aleatorio.nums2(8));
	}

	public String getString() {
		return aleatorio.nums2(8);
	}
}
