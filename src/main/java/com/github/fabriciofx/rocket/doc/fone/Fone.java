package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Documento;

public interface Fone extends Documento {
	enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}

	enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO,
		DESCONHECIDO;
	}
	
	void apague() throws IOException;

	void atualize(String numero, Tipo tipo, Operadora operadora)
		throws IOException;
}
