package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.id.Id;

public interface PessoasFisicas {
	PessoaFisica pessoaFisica(Nome nome, String endereco, String fone,
		String cpf, String rg) throws IOException;
	
	PessoaFisica pessoaFisica(Id id) throws IOException;
}
