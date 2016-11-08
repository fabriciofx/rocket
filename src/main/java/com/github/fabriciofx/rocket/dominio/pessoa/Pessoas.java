package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas {
	Pessoa pessoa(Nome nome, Cpf cpf, Rg rg,
		Endereco endereco, Fones fones) throws IOException;
	
//	Pessoa pessoa(Nome nome, Fones fones, Map<String, Documento> documentos)
//		throws IOException;
	
	Pessoa pessoa(Id id) throws IOException;
	
	List<Pessoa> todas() throws IOException;
}
