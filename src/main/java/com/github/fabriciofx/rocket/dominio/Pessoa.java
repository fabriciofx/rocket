package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.media.Printer;

public interface Pessoa extends Identificavel, Printer {
	Nome nome() throws IOException;

	Documentos documentos() throws IOException;

	Pessoa salva(Pessoa origem) throws IOException;
}
