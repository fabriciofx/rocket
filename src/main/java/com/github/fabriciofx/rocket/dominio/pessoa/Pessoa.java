package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.media.Printer;

public interface Pessoa extends Printer {
	Nome nome() throws IOException;

	Fones fones() throws IOException;
	
	void atualize(Nome nome, Cpf cpf, Rg rg, Endereco endereco)
		throws IOException;
}
