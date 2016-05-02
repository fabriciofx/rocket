package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.dominio.Literal;

public final class Numero extends Literal implements Elemento {
	public Numero(final String numero) {
		super(numero);
	}
}
