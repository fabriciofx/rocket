package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas {
	Pessoa pessoa(Id id) throws IOException;
	
	Pessoa salva(String nome, List<String> fones) throws IOException;
}
