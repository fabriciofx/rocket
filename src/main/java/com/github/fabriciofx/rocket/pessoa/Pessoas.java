package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas {
	Pessoa pessoa(Nome nome, String endereco, String fone) throws IOException;
	
	Pessoa pessoa(Id id) throws IOException;
}
