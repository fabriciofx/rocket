package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Documentos;
import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas {
	Pessoa pessoa(Nome nome, Documentos documentos) throws IOException;
	
	Pessoa pessoa(Id id) throws IOException;
	
	List<Pessoa> todas() throws IOException;
}
