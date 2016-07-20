package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Pessoa extends Printer {
	// Retorna os telefones da pessoa
	Fones fones() throws IOException;
}
