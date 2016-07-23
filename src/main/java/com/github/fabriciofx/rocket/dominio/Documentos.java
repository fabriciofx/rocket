package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.doc.Rg;
import com.github.fabriciofx.rocket.dominio.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.doc.Tratamento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.media.Printer;

public interface Documentos extends Printer {
	Cpf cpf() throws IOException;
	
	Rg rg() throws IOException;
	
	Sexo sexo() throws IOException;
	
	Tratamento tratamento() throws IOException;
	
	Endereco endereco() throws IOException;
	
	Fones fones() throws IOException;
}
