package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;

public interface Pessoas {
	void pessoa(Nome nome, Sexo sexo, Tratamento tratamento, Cpf cpf, Rg rg,
			Endereco endereco, Fone... fones) throws IOException;

	Pessoa pessoa(Id id) throws IOException;

	Iterable<Pessoa> todos() throws IOException;
}
