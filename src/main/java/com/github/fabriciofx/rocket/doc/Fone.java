package com.github.fabriciofx.rocket.doc;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fone extends Printer {
	enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}

	enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO,
		DESCONHECIDO;
	}

	String numero() throws IOException;

	Tipo tipo() throws IOException;

	Operadora operadora() throws IOException;
}
