package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.Documentos;
import com.github.fabriciofx.rocket.media.Printer;

public interface Pessoa extends Printer {
	Nome nome() throws IOException;

	Documentos documentos() throws IOException;
}
