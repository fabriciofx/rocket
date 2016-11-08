package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.dominio.fone.Fones;
import com.github.fabriciofx.rocket.dominio.pessoa.docs.Documentos;
import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas<T> {
	T pessoa(Nome nome, Documentos documentos, Fones fones) throws IOException;
	
	T pessoa(Id id) throws IOException;
	
	List<T> todas() throws IOException;
}
