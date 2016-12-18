package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas {
	Pessoa pessoa(String nome, Map<String, String> documentos)
		throws IOException;
	
	Pessoa pessoa(Id id) throws IOException;
}
