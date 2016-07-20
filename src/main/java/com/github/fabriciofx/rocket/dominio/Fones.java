package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

public interface Fones {
	Fone fone(long pessoaId) throws IOException;

	// Salva um número de telefone
	void salva(String numero) throws IOException;
}
