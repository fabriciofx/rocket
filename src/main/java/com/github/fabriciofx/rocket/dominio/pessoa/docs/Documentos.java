package com.github.fabriciofx.rocket.dominio.pessoa.docs;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.Sexo;
import com.github.fabriciofx.rocket.doc.Tratamento;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.media.Printer;

public interface Documentos extends Printer {
	Cpf cpf() throws IOException;
	
	Rg rg() throws IOException;
	
	Sexo sexo() throws IOException;
	
	Tratamento tratamento() throws IOException;
	
	Endereco endereco() throws IOException;
}
