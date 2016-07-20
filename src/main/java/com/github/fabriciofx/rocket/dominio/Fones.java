package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fones extends Printer {
	Fone fone(long pessoaId) throws IOException;

	// Salva um número de telefone
	void salva(String numero) throws IOException;
}
