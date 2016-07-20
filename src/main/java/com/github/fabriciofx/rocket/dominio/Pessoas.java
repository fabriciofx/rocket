package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;
import java.util.List;

public interface Pessoas {
	// Retorna uma pessoa pelo id
	Pessoa pessoa(long id) throws IOException;
	
	// Salva uma pessoa
	Pessoa salva(String nome, List<String> fones) throws IOException;
}
