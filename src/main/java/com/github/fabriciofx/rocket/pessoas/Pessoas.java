package com.github.fabriciofx.rocket.pessoas;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.pessoa.Pessoa;

public interface Pessoas {
	Pessoa fisica(String nome, Map<String, String> documentos)
		throws IOException;

	Pessoa juridica(String nome, Map<String, String> documentos)
			throws IOException;	
	
	Pessoa pessoa(Id id) throws IOException;
}
