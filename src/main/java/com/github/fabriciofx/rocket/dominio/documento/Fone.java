package com.github.fabriciofx.rocket.dominio.documento;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public final class Fone implements Documento {
	public enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO,
		DESCONHECIDO;
	}

	public enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}
	
	private final transient String numero;
	private final transient Tipo tipo;
	private final transient Operadora operadora;
	
	public Fone(final String numero, final Tipo tipo,
			final Operadora operadora) {
		this.numero = numero;
		this.tipo = tipo;
		this.operadora = operadora;
	}
	
	@Override
	public Media print(final Media media) throws IOException {
		return media.with("numero", numero)
			.with("tipo", tipo.toString())
			.with("operadora", operadora.toString());
	}
}
