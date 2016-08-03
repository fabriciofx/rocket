package com.github.fabriciofx.rocket.dominio.pessoa.doc;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.fone.Fones;
import com.github.fabriciofx.rocket.media.Printer;

public interface Documentos extends Printer {
	Cpf cpf() throws IOException;
	
	Rg rg() throws IOException;
	
	Sexo sexo() throws IOException;
	
	Tratamento tratamento() throws IOException;
	
	Endereco endereco() throws IOException;
	
	Fones fones() throws IOException;
}
