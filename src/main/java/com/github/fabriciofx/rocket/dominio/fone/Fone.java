package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fone extends Printer {
	String numero() throws IOException;

	void salva() throws IOException;

	void apaga() throws IOException;
}
