package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.media.Media;

public interface Pessoa {
	Nome nome() throws IOException;

	void nome(Nome nome) throws IOException;

	Sexo sexo() throws IOException;

	void sexo(Sexo sexo) throws IOException;

	Tratamento tratamento() throws IOException;

	void tratamento(Tratamento tratamento) throws IOException;

	Cpf cpf() throws IOException;

	void cpf(Cpf cpf) throws IOException;

	Rg rg() throws IOException;

	void rg(Rg rg) throws IOException;

	Endereco endereco() throws IOException;

	void endereco(Endereco endereco) throws IOException;

	Iterable<Fone> fones() throws IOException;

	void fones(Iterable<Fone> fones) throws IOException;
	
	Media print(Media media) throws IOException;
}
