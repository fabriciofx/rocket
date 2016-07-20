package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

public interface Pessoas {
	// Retorna uma pessoa pelo id
	Pessoa pessoa(long id) throws IOException;
	
	// Salva uma pessoa
	void salva(String nome) throws IOException;
}
