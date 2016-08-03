package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fone extends Printer {
	enum Tipo {
		CELULAR, FIXO, RADIO;
	}

	enum Operadora {
		CLARO, OI, TIM, VIVO;
	}

	String numero() throws IOException;

	Tipo tipo() throws IOException;

	Operadora operadora() throws IOException;

	void atualiza(String numero, Tipo tipo, Operadora operadora)
		throws IOException;

	void apaga() throws IOException;
}
